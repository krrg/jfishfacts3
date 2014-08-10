package fishfacts.controllers.impl;

import fishfacts.controllers.AbstractController;
import fishfacts.model.GameState;
import fishfacts.model.IGameModel;
import fishfacts.model.IGameStateListener;
import fishfacts.views.IStatusView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by krr428 on 7/5/14.
 */
public class StatusController extends AbstractController implements ActionListener
{
    private IStatusView view = null;
    private Timer gameClock = null;
    private int remainingTime = 0;
    private Map<GameState, IGameStateListener> stateHandlers = null;

    public StatusController(IGameModel model, IStatusView view)
    {
        super(model);
        this.view = view;
        this.stateHandlers = new HashMap<>();
        initStateHandlers();
        initGameClock();
    }

    private void initGameClock()
    {
        remainingTime = getModel().getSettings().getTotalGameTime();
        System.out.println("Remaining time init = " + remainingTime);
        gameClock = new Timer(1000, this);
    }

    private void initStateHandlers()
    {
        IGameStateListener active = new ActiveStateHandler();

        stateHandlers.put(GameState.START_SCREEN, new InactiveStateHandler());
        stateHandlers.put(GameState.PRE_START, new AboutToBeginHandler());
        stateHandlers.put(GameState.ACTIVE_GAME_PENDING, active);
        stateHandlers.put(GameState.ACTIVE_GAME_CORRECT_ANSWER, new PostCorrectAnswerHandler());
        stateHandlers.put(GameState.ACTIVE_GAME_WRONG_ANSWER, active);
        stateHandlers.put(GameState.POST_ACTIVE_GAME, new PostActiveStateHandler());
        stateHandlers.put(GameState.POST_ACTIVE_GAME_WON, new PostActiveGameMessageHandler());
        stateHandlers.put(GameState.POST_ACTIVE_GAME_LOST, new PostActiveGameMessageHandler());
    }

    @Override
    public void stateChanged(GameState newState)
    {
        if (stateHandlers.get(newState) != null)
        {
            stateHandlers.get(newState).stateChanged(newState);
        }
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent)
    {
        if (remainingTime <= 0)
        {
            System.out.println("Remaining time = " + remainingTime);
            getModel().requestStateChange(GameState.POST_ACTIVE_GAME);
            remainingTime = getTotalTime();
            gameClock.stop();
        }
        else
        {
            remainingTime -= 1;
            view.setTime(remainingTime, getTotalTime());
        }
    }

    private int getTotalTime()
    {
        return getModel().getSettings().getTotalGameTime();
    }

    private int getFishCapacity()
    {
        return getModel().getSettings().getTankCapacity();
    }

    private int getCurrentNumFish()
    {
        int result = getModel().getAquarium().getTankContents().size();
        System.out.println("\t\t<Aquarium Size> = " + result);
        return  result;
    }

    private void refreshGameStats()
    {
        int totalTime = getTotalTime();
        int fishCapacity = getFishCapacity();
        int currentFish = getCurrentNumFish();

        if (view != null)
        {
            view.setTime(remainingTime, totalTime);
            view.setFish(currentFish, fishCapacity);
        }
    }

    private class InactiveStateHandler implements IGameStateListener
    {
        @Override
        public void stateChanged(GameState newState)
        {
            view.resetView();
        }
    }

    private class ActiveStateHandler implements IGameStateListener
    {
        @Override
        public void stateChanged(GameState newState)
        {
            if (getCurrentNumFish() >= getFishCapacity())
            {
                System.out.println("Detected at capacity.");
                getModel().requestStateChange(GameState.POST_ACTIVE_GAME);
                getModel().requestStateChange(GameState.POST_ACTIVE_GAME_WON);
                getModel().requestStateChange(GameState.START_SCREEN);
            }
            refreshGameStats();
        }
    }

    private class PostActiveGameMessageHandler implements IGameStateListener
    {
        @Override
        public void stateChanged(GameState newState) {
            if (newState == GameState.POST_ACTIVE_GAME_WON) {
                javax.swing.JOptionPane.showMessageDialog((Component) view, "Good job! You've filled the tank!");
            }
            else if (newState == GameState.POST_ACTIVE_GAME_LOST)
            {
                javax.swing.JOptionPane.showMessageDialog((Component) view, "Oh no! You ran out of time!");
            }
        }
    }

    private class PostCorrectAnswerHandler implements IGameStateListener
    {
        @Override
        public void stateChanged(GameState newState) {


            refreshGameStats();
        }
    }

    private class PostActiveStateHandler implements IGameStateListener
    {

        @Override
        public void stateChanged(GameState newState)
        {
            gameClock.stop();
        }
    }

    private class AboutToBeginHandler implements IGameStateListener
    {

        @Override
        public void stateChanged(GameState newState)
        {
            remainingTime = getTotalTime();
            gameClock.start();
        }
    }
}
