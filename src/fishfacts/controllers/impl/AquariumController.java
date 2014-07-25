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
import fishfacts.model.aqua.impl.FishBuilder;
import fishfacts.views.IAquariumView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
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
    private Timer redrawClock = null;
    private IAquariumView view = null;
    private final int NEW_BUBBLES = 3;

    public AquariumController(IGameModel model, IAquariumView view)
    {
        super(model);
        this.fishAquarium = getModel().getAquarium();
        this.bubbleAquarium = new Aquarium(fishAquarium.getWidth(), fishAquarium.getHeight());
        this.stateHandlers = new HashMap<>();

        this.view = view;
        this.view.setAquariumController(this);

        initStateHandlers();
        initSwimClock();
        initNewBubbleClock();
        initRedrawClock();

    }

    private void initStateHandlers()
    {
        //TODO: Finish this section
        stateHandlers.put(GameState.PRE_START, null);
        stateHandlers.put(GameState.ACTIVE_GAME_CORRECT_ANSWER, new CorrectAnswerHandler());
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

    private void initRedrawClock()
    {
        redrawClock = new Timer(25, null);
        redrawClock.addActionListener(new RedrawHandler());
        redrawClock.start();
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

    private class RedrawHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent actionEvent)
        {
            System.out.println("About to draw " + bubbleAquarium.getTankContents().size() + " bubbles.");
            view.updateBuffer(drawBuffer());
            view.requestRedraw();
        }

        private BufferedImage drawBuffer()
        {
            int width = bubbleAquarium.getWidth();
            int height = bubbleAquarium.getHeight();

            BufferedImage buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = buffer.createGraphics();

            drawAquarium(g2d, fishAquarium);
            drawAquarium(g2d, bubbleAquarium);

            return buffer;
        }

        private void drawAquarium(Graphics2D g2d, IAquarium aqua)
        {
            g2d.drawImage(aqua.getBackdrop(), 0, 0, null);
            for (IAquariumObject obj: aqua)
            {
                g2d.drawImage(obj.getImage(), (int)obj.getX(), (int)obj.getY(), null);
            }
        }


    }

    private class NewBubbleHandler implements ActionListener
    {
        private Random rand = new Random();

        private Bubble createRandomBubble()
        {
            int x = rand.nextInt(bubbleAquarium.getWidth());
            int y = bubbleAquarium.getHeight();
            double scaling = rand.nextDouble() + 0.1; //This is so the height and width are rounded correctly.
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
            addMoreBubbles();
        }
    }

    private class SwimHandler implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent actionEvent)
        {
//            for (IAquariumObject obj: fishAquarium)
//            {
//                obj.calculateNewPosition(swimClock.getDelay());
//            }
//
//            for (IAquariumObject obj: bubbleAquarium)
//            {
//                obj.calculateNewPosition(swimClock.getDelay());
//            }

            fishAquarium.calculateNewPositions(swimClock.getDelay());
            bubbleAquarium.calculateNewPositions(swimClock.getDelay());

            //Do not redraw here.
        }
    }

    private class CorrectAnswerHandler implements IGameStateListener
    {
        private int correctAnswers = 0;

        @Override
        public void stateChanged(GameState newState)
        {
            correctAnswers += 1;

            if (correctAnswers >= getCorrectAnswersPerFish())
            {
                correctAnswers = 0;
                addFish();
            }
        }

        private void addFish()
        {
            fishAquarium.addObject(createRandomFish());
        }

        private IAquariumObject createRandomFish()
        {
            return FishBuilder.getInstance()
                    .createRandomFish(fishAquarium.getWidth(), fishAquarium.getHeight());
        }

        private int getCorrectAnswersPerFish()
        {
            return getModel().getSettings().getCorrectPerFish();
        }
    }

}
