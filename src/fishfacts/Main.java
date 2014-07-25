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
import fishfacts.views.impl.AnswerView;
import fishfacts.views.impl.AquariumView;

import javax.swing.*;
import java.awt.*;

/**
 * Created by krr428 on 7/3/14.
 */
public class Main
{
    public static void main(String[] args)
    {
        JFrame jf = new JFrame("JFishFacts 3");
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jf.add(createMainView());

        jf.pack();
        jf.setVisible(true);
    }

    public static JComponent createMainView()
    {
//        IAnswerView answerView = null;
//        IAquariumView aquariumView = null;
//        IBottomView bottomView = null;
//        IStartButtonView startButtonView = null;
//        IStatusView statusView = null;
//
//        IGameModel model = GameModel.getInstance();
//
//        IAnswerController answerController = new AnswerController(model, answerView);
//        IAquariumController aquariumController = new AquariumController(model, aquariumView);
//        ISettingsController settingsController = new SettingsController(model);
//        IStartButtonController startButtonController = new StartButtonController(model, startButtonView);
//
//        return null;

        AnswerView answerView = new AnswerView();
        AquariumView aquariumView = new AquariumView();

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        mainPanel.add(aquariumView, BorderLayout.CENTER);
        mainPanel.add(answerView, BorderLayout.SOUTH);

        answerView.setAnswerController(new AnswerController(GameModel.getInstance(), answerView));
        aquariumView.setAquariumController(new AquariumController(GameModel.getInstance(), aquariumView));

        return mainPanel;
    }
}
