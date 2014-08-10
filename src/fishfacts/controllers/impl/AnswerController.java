package fishfacts.controllers.impl;

import fishfacts.controllers.AbstractController;
import fishfacts.controllers.IAnswerController;
import fishfacts.model.GameState;
import fishfacts.model.IGameModel;
import fishfacts.model.aqua.IAquarium;
import fishfacts.model.settings.IGameSettings;
import fishfacts.model.operations.ProblemTuple;
import fishfacts.model.operations.RandomTupleBuilder;
import fishfacts.views.IAnswerView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by krr428 on 7/5/14.
 */
public class AnswerController extends AbstractController implements IAnswerController
{
    private IGameSettings<Integer> gameSettings = null;
    private IAquarium aquarium = null;
    private ProblemTuple<Integer> currentProblem = null;
    private boolean wrongAnswerFlag = false;
    private IAnswerView view = null;

    public AnswerController(IGameModel model, IAnswerView view)
    {
        super(model);
        this.view = view;
        this.view.setController(this);
    }

    @Override
    public void handleAnswer(String userAnswer)
    {
        if (getModel().getCurrentState() != GameState.ACTIVE_GAME_PENDING)
        {
            throw new IllegalStateException("User gave answer while we were not looking for it.\n" +
                    "Actual state was: " + getModel().getCurrentState());
        }

        if (! isValidAnswer(userAnswer))
        {
            getModel().requestStateChange(GameState.ACTIVE_GAME_WRONG_ANSWER);
        }
        else if (! currentProblem.isCorrect(Integer.parseInt(userAnswer)))
        {
            getModel().requestStateChange(GameState.ACTIVE_GAME_WRONG_ANSWER);
        }
        else
        {
            getModel().requestStateChange(GameState.ACTIVE_GAME_CORRECT_ANSWER);
        }
    }

    private boolean isValidAnswer(String answer)
    {
        try
        {
            Integer.parseInt(answer);
            return true;
        }
        catch (NumberFormatException ex)
        {
            return false;
        }
    }

    @Override
    public void stateChanged(GameState newState)
    {
        if (newState == GameState.ACTIVE_GAME_PENDING)
        {
            handlePendingAnswer();
        }
        else if (newState == GameState.ACTIVE_GAME_WRONG_ANSWER)
        {
            handleWrongAnswer();
        }
        else if (newState == GameState.ACTIVE_GAME_CORRECT_ANSWER)
        {
            handleCorrectAnswer();
        }
    }

    private void handlePendingAnswer()
    {
        if (!wrongAnswerFlag)
        {
            generateNewProblem();
        }
        view.clearFields();
        view.focusAnswerField();
        refreshView();
    }

    private void handleCorrectAnswer()
    {
        getModel().requestStateChange(GameState.ACTIVE_GAME_PENDING);
    }

    private void handleWrongAnswer()
    {
        wrongAnswerFlag = true;
        view.freezeView();
        final Timer unfreezeTimer = new Timer(getIncorrectTimeout(), null);
        unfreezeTimer.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                unfreezeTimer.stop();
                view.unfreezeView();
                getModel().requestStateChange(GameState.ACTIVE_GAME_PENDING);
                wrongAnswerFlag = false;
            }
        });

        unfreezeTimer.setRepeats(false);
        unfreezeTimer.start();
    }

    private void generateNewProblem()
    {
        this.currentProblem = RandomTupleBuilder.getInstance().generateProblem(getModel().getSettings());
    }

    private void refreshView()
    {
        view.clearFields();
        view.setOperator(String.valueOf(currentProblem.getOperator().getSymbol()));
        view.setTermA(String.valueOf(currentProblem.getOperandA()));
        view.setTermB(String.valueOf(currentProblem.getOperandB()));
        view.focusAnswerField();
    }

    private int getIncorrectTimeout()
    {
        return getModel().getSettings().getIncorrectTimeout();
    }
}
