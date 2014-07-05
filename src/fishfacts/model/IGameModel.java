package fishfacts.model;

import fishfacts.model.aqua.IAquarium;
import fishfacts.model.settings.IGameSettings;

/**
 * Created by krr428 on 7/3/14.
 */
public interface IGameModel
{
    public IGameSettings getSettings();

    public IAquarium getAquarium();

    public GameState getCurrentState();

    public void addGameStateListener(IGameStateListener listener);

    public void removeGameStateListener(IGameStateListener listener);

    public void requestStateChange(GameState newState);
}
