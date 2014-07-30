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
import fishfacts.views.impl.BottomView;
import fishfacts.views.impl.StartButtonView;

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

        jf.setSize(640, 480);
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
        StartButtonView startButtonView = new StartButtonView();
        BottomView bottomView = new BottomView(startButtonView, answerView);
        AquariumView aquariumView = new AquariumView();

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        mainPanel.add(aquariumView, BorderLayout.CENTER);
        mainPanel.add(bottomView, BorderLayout.SOUTH);

        answerView.setController(new AnswerController(GameModel.getInstance(), answerView));
        startButtonView.setController(new StartButtonController(GameModel.getInstance(), startButtonView));
        aquariumView.setController(new AquariumController(GameModel.getInstance(), aquariumView));


        return mainPanel;
    }
}
