package fishfacts.model;

import fishfacts.model.aqua.IAquarium;
import fishfacts.model.settings.IGameSettings;
import fishfacts.model.settings.SimpleGameSettings;

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

    private GameModel()
    {
        this.settings = new SimpleGameSettings();
        this.aquarium = null;
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
}
