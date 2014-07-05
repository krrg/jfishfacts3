package fishfacts.controllers;

import fishfacts.model.settings.IGameSettings;

/**
 * Created by krr428 on 7/5/14.
 */
public interface ISettingsController
{
    public IGameSettings<Integer> getCurrentSettings();
}
