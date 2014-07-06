package fishfacts.controllers.impl;

import fishfacts.controllers.AbstractController;
import fishfacts.model.GameState;
import fishfacts.model.IGameModel;
import fishfacts.model.IGameStateListener;
import fishfacts.views.IStatusView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by krr428 on 7/5/14.
 */
public class StatusBarController extends AbstractController implements ActionListener
{
    private IStatusView view = null;
    private Timer gameClock = null;
    private int remainingTime = 0;
    private Map<GameState, IGameStateListener> stateHandlers = null;

    public StatusBarController(IGameModel model, IStatusView view)
    {
        super(model);
        this.view = view;
        this.stateHandlers = new HashMap<>();
        initStateHandlers();
        initGameClock();
    }

    private void initGameClock()
    {
        gameClock = new Timer(1000, this);
        if (gameClock.isRunning())
        {
            gameClock.stop();
        }
    }

    private void initStateHandlers()
    {
        IGameStateListener active = new ActiveStateHandler();

        stateHandlers.put(GameState.START_SCREEN, new InactiveStateHandler());
        stateHandlers.put(GameState.PRE_START, new AboutToBeginHandler());
        stateHandlers.put(GameState.ACTIVE_GAME_PENDING, active);
        stateHandlers.put(GameState.ACTIVE_GAME_CORRECT_ANSWER, active);
        stateHandlers.put(GameState.ACTIVE_GAME_WRONG_ANSWER, active);
        stateHandlers.put(GameState.POST_ACTIVE_GAME, new PostActiveStateHandler());
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
            getModel().requestStateChange(GameState.POST_ACTIVE_GAME);
            remainingTime = getTotalTime();
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
        return getModel().getAquarium().getTankContents().size();
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
            int totalTime = getTotalTime();
            int fishCapacity = getFishCapacity();
            int currentFish = getCurrentNumFish();

            view.setTime(remainingTime, totalTime);
            view.setFish(currentFish, fishCapacity);
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
            gameClock.start();
        }
    }
}
