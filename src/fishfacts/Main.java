package fishfacts;

import com.sun.net.httpserver.HttpServer;
import fishfacts.controllers.impl.*;
import fishfacts.model.GameModel;
import fishfacts.views.impl.*;
import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import javax.swing.*;
import javax.ws.rs.core.UriBuilder;
import java.awt.*;
import java.net.URI;

/**
 * Created by krr428 on 7/3/14.
 */
public class Main
{
    public static void main(String[] args)
    {
        startAdminServer();
        startGUI();
    }

    private static void startGUI()
    {
        JFrame jf = new JFrame("JFishFacts 3");
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jf.add(createMainView());

        jf.setSize(640, 480);
        jf.setVisible(true);
    }

    private static void startAdminServer()
    {
        URI baseUri = UriBuilder.fromUri("http://0.0.0.0/").port(8686).build();
        ResourceConfig config = new ResourceConfig(SettingsController.class);
        HttpServer server = JdkHttpServerFactory.createHttpServer(baseUri, config);

        System.out.println("Server is listening on " + server.getAddress());
    }

    private static JComponent createMainView()
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
        StatusView statusView = new StatusView();

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        mainPanel.add(aquariumView, BorderLayout.CENTER);
        mainPanel.add(bottomView, BorderLayout.SOUTH);
        mainPanel.add(statusView, BorderLayout.NORTH);

        answerView.setController(new AnswerController(GameModel.getInstance(), answerView));
        startButtonView.setController(new StartButtonController(GameModel.getInstance(), startButtonView));
        aquariumView.setController(new AquariumController(GameModel.getInstance(), aquariumView));
        new BottomController(GameModel.getInstance(), bottomView);
        new StatusController(GameModel.getInstance(), statusView);

        return mainPanel;
    }
}
