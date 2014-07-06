package fishfacts.controllers.impl;

import fishfacts.controllers.AbstractController;
import fishfacts.controllers.IAquariumController;
import fishfacts.model.GameState;
import fishfacts.model.IGameModel;
import fishfacts.model.IGameStateListener;
import fishfacts.model.aqua.IAquarium;
import fishfacts.model.aqua.IAquariumObject;
import fishfacts.model.aqua.impl.Aquarium;
import fishfacts.model.aqua.impl.Bubble;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Dimension2D;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by krr428 on 7/5/14.
 */
public class AquariumController extends AbstractController implements IAquariumController
{
    private IAquarium fishAquarium = null;
    private IAquarium bubbleAquarium = null;
    private Map<GameState, IGameStateListener> stateHandlers = null;
    private Timer swimClock = null;
    private Timer newBubbleClock = null;
    final int NEW_BUBBLES = 10;

    public AquariumController(IGameModel model)
    {
        super(model);
        this.fishAquarium = getModel().getAquarium();
        this.bubbleAquarium = new Aquarium(fishAquarium.getWidth(), fishAquarium.getHeight());
        this.stateHandlers = new HashMap<>();

        initStateHandlers();
        initSwimClock();
        initNewBubbleClock();
    }

    private void initStateHandlers()
    {
        stateHandlers.put(GameState.PRE_START, null);
    }

    //The swim clock moves the fish and the bubbles every 25ms.
    private void initSwimClock()
    {
        swimClock = new Timer(25, null);
        swimClock.addActionListener(new SwimHandler());
        swimClock.start();
    }

    //The bubble clock creates 10 new bubbles every 500ms
    private void initNewBubbleClock()
    {
        newBubbleClock = new Timer(500, null);
        newBubbleClock.addActionListener(new NewBubbleHandler());
        newBubbleClock.start();
    }

    @Override
    public void handleResize(Dimension newSize)
    {
        int width = (int)newSize.getWidth();
        int height = (int)newSize.getHeight();

        fishAquarium.setWidth(width);
        fishAquarium.setHeight(height);
        bubbleAquarium.setWidth(width);
        bubbleAquarium.setHeight(height);
    }

    @Override
    public void stateChanged(GameState newState)
    {
        if (stateHandlers.get(newState) != null)
        {
            stateHandlers.get(newState).stateChanged(newState);
        }
    }


    private class NewBubbleHandler implements ActionListener
    {
        private Random rand = new Random();

        private Bubble createRandomBubble()
        {
            int x = rand.nextInt(bubbleAquarium.getWidth());
            int y = bubbleAquarium.getHeight();
            double scaling = rand.nextDouble();
            return new Bubble(x, y, scaling, 1.0 / scaling);
        }

        private void addMoreBubbles()
        {
            for (int i = 0; i < NEW_BUBBLES; i++)
            {
                bubbleAquarium.addObject(createRandomBubble());
            }
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent)
        {
            System.out.println("New bubbles on their way...");
        }
    }

    private class SwimHandler implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent actionEvent)
        {
            for (IAquariumObject obj: fishAquarium)
            {
                obj.calculateNewPosition(swimClock.getDelay());
            }

            for (IAquariumObject obj: bubbleAquarium)
            {
                obj.calculateNewPosition(swimClock.getDelay());
            }

            //Do not redraw here.
        }
    }

}
