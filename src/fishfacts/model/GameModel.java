package fishfacts.model;

import fishfacts.model.aqua.IAquarium;
import fishfacts.model.settings.IGameSettings;
import fishfacts.model.settings.SimpleGameSettings;

import java.util.HashSet;
import java.util.Set;

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

    private GameModel()
    {
        this.settings = new SimpleGameSettings();
        this.aquarium = null;
        this.stateListenerSet = new HashSet<>();
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
        this.currentState = newState;
        for (IGameStateListener listener: stateListenerSet)
        {
            listener.stateChanged(currentState);
        }
    }
}
