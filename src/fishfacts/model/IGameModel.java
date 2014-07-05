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


}
