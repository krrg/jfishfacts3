package fishfacts;

import fishfacts.controllers.IAnswerController;
import fishfacts.controllers.IAquariumController;
import fishfacts.controllers.ISettingsController;
import fishfacts.controllers.IStartButtonController;
import fishfacts.controllers.impl.AnswerController;
import fishfacts.controllers.impl.AquariumController;
import fishfacts.controllers.impl.SettingsController;
import fishfacts.controllers.impl.StartButtonController;
import fishfacts.model.GameModel;
import fishfacts.model.IGameModel;
import fishfacts.model.aqua.impl.Aquarium;
import fishfacts.model.aqua.impl.Bubble;
import fishfacts.views.*;

import javax.swing.*;
import java.awt.*;

/**
 * Created by krr428 on 7/3/14.
 */
public class Main
{
    public static void main(String[] args)
    {

    }

    public static JComponent createMainView()
    {
        IAnswerView answerView = null;
        IAquariumView aquariumView = null;
        IBottomView bottomView = null;
        IStartButtonView startButtonView = null;
        IStatusView statusView = null;

        IGameModel model = GameModel.getInstance();

        IAnswerController answerController = new AnswerController(model, answerView);
        IAquariumController aquariumController = new AquariumController(model, aquariumView);
        ISettingsController settingsController = new SettingsController(model);
        IStartButtonController startButtonController = new StartButtonController(model, startButtonView);


    }
}
