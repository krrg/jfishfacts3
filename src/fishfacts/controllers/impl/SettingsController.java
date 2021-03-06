package fishfacts.controllers.impl;

import com.google.gson.*;
import fishfacts.controllers.AbstractController;
import fishfacts.controllers.ISettingsController;
import fishfacts.model.GameModel;
import fishfacts.model.GameState;
import fishfacts.model.operations.*;
import fishfacts.model.settings.IGameSettings;
import org.stringtemplate.v4.ST;

import javax.print.DocFlavor;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.util.*;

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
        ST template = new ST(readTextContent("settings.html"), '$', '$');

        template.add("csTotal", getGameSettings().getTotalGameTime());
        template.add("csDelay", getGameSettings().getIncorrectTimeout());
        template.add("asTotalFish", getGameSettings().getTankCapacity());
        template.add("asCorrectAnswers", getGameSettings().getCorrectPerFish());
        template.add("fsAddEnabled", isOperationEnabled(Add.getInstance()));
        template.add("fsSubEnabled", isOperationEnabled(Subtract.getInstance()));
        template.add("fsMultEnabled", isOperationEnabled(Multiply.getInstance()));
        template.add("fsDivEnabled", isOperationEnabled(Divide.getInstance()));

        template.add("fsAddVals0", getJsonArray(getGameSettings().getAllowedOperandsFor(Add.getInstance(), 0)));
        template.add("fsAddVals1", getJsonArray(getGameSettings().getAllowedOperandsFor(Add.getInstance(), 1)));
        template.add("fsSubVals0", getJsonArray(getGameSettings().getAllowedOperandsFor(Subtract.getInstance(), 0)));
        template.add("fsSubVals1", getJsonArray(getGameSettings().getAllowedOperandsFor(Subtract.getInstance(), 1)));
        template.add("fsMultVals0", getJsonArray(getGameSettings().getAllowedOperandsFor(Multiply.getInstance(), 0)));
        template.add("fsMultVals1", getJsonArray(getGameSettings().getAllowedOperandsFor(Multiply.getInstance(), 1)));
        template.add("fsDivVals0", getJsonArray(getGameSettings().getAllowedOperandsFor(Divide.getInstance(), 0)));
        template.add("fsDivVals1", getJsonArray(getGameSettings().getAllowedOperandsFor(Divide.getInstance(), 1)));

        return template.render();
    }

    public static <T> String  getJsonArray(Collection<T> values)
    {
        if (values == null)
        {
            return "";
        }
        return new Gson().toJson(values);
    }

    private boolean isOperationEnabled(AbstractOperator<Integer> operator)
    {
        return getGameSettings().getAllowedOperations().contains(operator);
    }

    @GET
    @Path("base.css")
    @Produces("text/css")
    public String getBaseCSS()
    {
        return readTextContent("base.css");
    }

    @GET
    @Path("display.js")
    @Produces("text/javascript")
    public String getDisplayJS()
    {
        return readTextContent("display.js");
    }

    @GET
    @Path("settingsController.js")
    @Produces("text/javascript")
    public String getSettingsController()
    {
        return readTextContent("settingsController.js");
    }

    public String readTextContent(String file)
    {
        StringBuilder sb = new StringBuilder();
        Scanner sc = new Scanner(SettingsController.class.getResourceAsStream("html/" + file));
        while (sc.hasNextLine())
        {
            sb.append(sc.nextLine()).append('\n');
        }
        return sb.toString();
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("/clockSettingsSave")
    public Response ajax_clockSettingsSave(MultivaluedMap<String, String> params)
    {
        String strTotalTime = params.getFirst("totalTime");
        String strDelayTime = params.getFirst("answerDelay");

        int totalTime = getPositiveInt(strTotalTime);
        int delayTime = getPositiveInt(strDelayTime);

        if (totalTime < 0)
        {
            return createInvalidNumberResponse(strTotalTime);
        }
        else if (delayTime < 0)
        {
            return createInvalidNumberResponse(strDelayTime);
        }

        getGameSettings().setTotalGameTime(totalTime);
        getGameSettings().setIncorrectTimeout(delayTime);

        return Response.ok("Your clock settings have been saved.").build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("/aquariumSettingsSave")
    public Response ajax_aquariumSettingsSave(MultivaluedMap<String, String> params)
    {
        String strTotalFish = params.getFirst("totalFish");
        String strNumCorrect = params.getFirst("numCorrect");

        int totalFish = getPositiveInt(strTotalFish);
        int numCorrect = getPositiveInt(strNumCorrect);

        if (totalFish < 0)
        {
            return createInvalidNumberResponse(strTotalFish);
        }
        else if (numCorrect < 0)
        {
            return createInvalidNumberResponse(strNumCorrect);
        }

        getGameSettings().setTankCapacity(totalFish);
        getGameSettings().setCorrectPerFish(numCorrect);

        return Response.ok("Your aquarium settings have been saved.").build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("/factSettingsSave")
    public Response ajax_factSettingsSave(MultivaluedMap<String, String> params)
    {
        setOperatorSettings(
                Add.getInstance(),
                params.getFirst("add[enabled]"),
                params.get("add[terms0][]"),
                params.get("add[terms1][]")
        );
        setOperatorSettings(
                Subtract.getInstance(),
                params.getFirst("sub[enabled]"),
                params.get("sub[terms0][]"),
                params.get("sub[terms1][]")
        );
        setOperatorSettings(
                Multiply.getInstance(),
                params.getFirst("mult[enabled]"),
                params.get("mult[terms0][]"),
                params.get("mult[terms1][]")
        );
        setOperatorSettings(
                Divide.getInstance(),
                params.getFirst("div[enabled]"),
                params.get("div[terms0][]"),
                params.get("div[terms1][]")
        );

        return Response.ok("Your fact settings have been saved.").build();
    }

    private void setOperatorSettings(AbstractOperator<Integer> operator, String strEnabled, List<String> rawAllowed0, List<String> rawAllowed1)
    {
        boolean enabled = strEnabled.contains("true");
        Set<Integer> allowed0 = new HashSet<>(toIntegerList(rawAllowed0));
        Set<Integer> allowed1 = new HashSet<>(toIntegerList(rawAllowed1));

        if (enabled)
        {
            getGameSettings().addAllowedOperation(operator);
        }
        else
        {
            getGameSettings().removeAllowedOperation(operator);
        }

        getGameSettings().setAllowedOperandsFor(operator, 0, allowed0);
        getGameSettings().setAllowedOperandsFor(operator, 1, allowed1);

    }

    private List<Integer> toIntegerList(List<String> strings)
    {
        List<Integer> integerList = new ArrayList<>();
        for (String s: strings)
        {
            integerList.add(Integer.parseInt(s));
        }
        return integerList;
    }

    private Response createInvalidNumberResponse(String invalidString)
    {
        return Response.status(400).entity("The server says: Sorry, '" + invalidString + "' doesn't look like a positive integer...").build();
    }

    private int getPositiveInt(String str)
    {
        try
        {
            return Integer.parseInt(str);
        }
        catch (NumberFormatException ex)
        {
            return -1;
        }
    }

    private IGameSettings<Integer> getGameSettings()
    {
        return GameModel.getInstance().getSettings();
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
