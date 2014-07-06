package fishfacts.controllers.impl;

import fishfacts.controllers.AbstractController;
import fishfacts.controllers.ISettingsController;
import fishfacts.model.GameState;
import fishfacts.model.IGameModel;
import fishfacts.model.settings.IGameSettings;

/**
 * Created by krr428 on 7/5/14.
 */
public class SettingsController extends AbstractController implements ISettingsController
{
    public SettingsController(IGameModel model)
    {
        super(model);
    }

    @Override
    public void stateChanged(GameState newState)
    {

    }

    @Override
    public void handleSettingsChange(IGameSettings<Integer> newSettings)
    {
        getModel().setSettings(newSettings);
    }
}
