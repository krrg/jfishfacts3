package fishfacts.controllers.impl;

import fishfacts.controllers.AbstractController;
import fishfacts.controllers.ISettingsController;
import fishfacts.model.GameState;
import fishfacts.model.IGameModel;
import fishfacts.model.settings.IGameSettings;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

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

    @Path("/")
    public static class WebStatusResource
    {
        @GET
        @Produces("text/html")
        public String getIndex()
        {
            System.out.println("This is a test!");
            return "Hello World";
        }
    }
}
