package fishfacts.model;

import fishfacts.model.aqua.IAquarium;
import fishfacts.model.aqua.impl.Aquarium;
import fishfacts.model.settings.IGameSettings;
import fishfacts.model.settings.SimpleGameSettings;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * Created by krr428 on 7/4/14.
 */
public class GameModel implements IGameModel
{
    private static GameModel instance = null;

    public static GameModel getInstance()
    {
        if (instance == null)
        {
            instance = new GameModel();
        }
        return instance;
    }

    private IGameSettings settings = null;
    private IAquarium aquarium = null;
    private GameState currentState = GameState.START_SCREEN;
    private Set<IGameStateListener> stateListenerSet = null;
    private Stack<GameState> stateChangeRequests = null;

    private GameModel()
    {
        this.settings = new SimpleGameSettings();
        this.aquarium = new Aquarium(640, 480);
        this.stateListenerSet = new HashSet<>();
        this.stateChangeRequests = new Stack<>();
    }


    @Override
    public IGameSettings getSettings()
    {
        return this.settings;
    }

    @Override
    public IAquarium getAquarium()
    {
        return this.aquarium;
    }

    @Override
    public GameState getCurrentState()
    {
        return currentState;
    }

    @Override
    public void addGameStateListener(IGameStateListener listener)
    {
        stateListenerSet.add(listener);
    }

    @Override
    public void removeGameStateListener(IGameStateListener listener)
    {
        stateListenerSet.remove(listener);
    }

    @Override
    public void requestStateChange(GameState newState)
    {
        stateChangeRequests.push(newState);

        if (stateChangeRequests.size() == 1)
        {
            while (! stateChangeRequests.isEmpty())
            {
                GameState next = stateChangeRequests.pop();
                if (next != currentState)
                {
                    fireStateChange(next);
                }
            }
        }
    }

    private void fireStateChange(GameState newState)
    {
        this.currentState = newState;
        for (IGameStateListener listener: stateListenerSet)
        {
            listener.stateChanged(currentState);
        }
    }

    @Override
    public void setSettings(IGameSettings<Integer> settings)
    {
        this.settings = settings;
    }
}
