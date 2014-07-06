package fishfacts.controllers.impl;

import fishfacts.controllers.AbstractController;
import fishfacts.controllers.IAnswerController;
import fishfacts.model.GameState;
import fishfacts.model.IGameModel;
import fishfacts.model.aqua.IAquarium;
import fishfacts.model.settings.IGameSettings;
import fishfacts.model.settings.ops.ProblemTuple;

/**
 * Created by krr428 on 7/5/14.
 */
public class AnswerController extends AbstractController implements IAnswerController
{
    private IGameSettings<Integer> gameSettings = null;
    private IAquarium aquarium = null;
    private ProblemTuple<Integer> currentProblem = null;

    public AnswerController(IGameModel model)
    {
        super(model);
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
            generateNewProblem();
        }
    }

    private void generateNewProblem()
    {

    }
}
