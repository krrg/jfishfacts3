package fishfacts.controllers.impl;

import fishfacts.controllers.AbstractController;
import fishfacts.controllers.ISettingsController;
import fishfacts.model.GameModel;
import fishfacts.model.GameState;
import fishfacts.model.IGameModel;
import fishfacts.model.settings.IGameSettings;
import org.glassfish.jersey.message.internal.MediaTypes;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Scanner;

/**
 * Created by krr428 on 7/5/14.
 */
@Path("")
public class SettingsController extends AbstractController implements ISettingsController
{
    public SettingsController()
    {
        super(GameModel.getInstance());
    }

    @GET
    @Produces("text/html")
    public String getString()
    {
        return readHTMLFile("settings.html");
    }

    @GET
    @Path("base.css")
    @Produces("text/css")
    public String getBaseCSS()
    {
        return readHTMLFile("base.css");
    }

    public String readHTMLFile(String file)
    {
        StringBuilder sb = new StringBuilder();
        Scanner sc = new Scanner(SettingsController.class.getResourceAsStream("html/" + file));
        while (sc.hasNextLine())
        {
            sb.append(sc.nextLine()).append('\n');
        }
        return sb.toString();
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
