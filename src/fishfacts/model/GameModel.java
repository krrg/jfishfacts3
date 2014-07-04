package fishfacts.model;

import fishfacts.model.aqua.IAquarium;
import fishfacts.model.settings.IGameSettings;

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

    private GameModel()
    {

    }


    @Override
    public IGameSettings getSettings()
    {
        return null;
    }

    @Override
    public IAquarium getAquarium()
    {
        return null;
    }
}
