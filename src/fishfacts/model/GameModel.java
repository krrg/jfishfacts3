package fishfacts.model;

import fishfacts.model.aqua.IAquarium;
import fishfacts.model.aqua.impl.Aquarium;
import fishfacts.model.settings.IGameSettings;
import fishfacts.model.settings.SimpleGameSettings;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;

/**
 * Created by krr428 on 7/4/14.
 */
public class GameModel implements IGameModel
{
    private static GameModel instance = null;

    public static GameModel getInstance()
    {
        if (instance == null)
        {
            instance = new GameModel();
        }
        return instance;
    }

    private IGameSettings settings = null;
    private IAquarium aquarium = null;
    private GameState currentState = GameState.START_SCREEN;
    private Set<IGameStateListener> stateListenerSet = null;
    private Queue<GameState> stateChangeRequests = null;

    private GameModel()
    {
        this.settings = new SimpleGameSettings();
        this.aquarium = new Aquarium(640, 480, initAquariumBackground());
        this.stateListenerSet = new HashSet<>();
        this.stateChangeRequests = new LinkedList<>();
    }

    private BufferedImage initAquariumBackground()
    {
        try
        {
            return ImageIO.read(Aquarium.class.getResource("res/aquabg.jpg"));
        }
        catch (IOException e)
        {
            System.out.println("Unable to load aquarium backdrop!\n" + e);
            return null;
        }
    }


    @Override
    public IGameSettings getSettings()
    {
        return this.settings;
    }

    @Override
    public IAquarium getAquarium()
    {
        return this.aquarium;
    }

    @Override
    public GameState getCurrentState()
    {
        return currentState;
    }

    @Override
    public void addGameStateListener(IGameStateListener listener)
    {
        stateListenerSet.add(listener);
    }

    @Override
    public void removeGameStateListener(IGameStateListener listener)
    {
        stateListenerSet.remove(listener);
    }

    @Override
    public void requestStateChange(GameState newState)
    {
        stateChangeRequests.add(newState);

        if (stateChangeRequests.size() == 1) {
            while (!stateChangeRequests.isEmpty()) {
                GameState next = stateChangeRequests.peek();
                if (next != currentState) {
                    fireStateChange(next);
                }
                stateChangeRequests.poll();
            }
        }

    }

    private void fireStateChange(GameState newState)
    {
        System.out.println("Firing state change for: " + newState);
        this.currentState = newState;
        for (IGameStateListener listener: stateListenerSet)
        {
            System.out.println("\tNotified " + listener);
            listener.stateChanged(currentState);
        }
    }

    @Override
    public void setSettings(IGameSettings<Integer> settings)
    {
        this.settings = settings;
    }
}
