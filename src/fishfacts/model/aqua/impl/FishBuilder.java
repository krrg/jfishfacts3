package fishfacts.model.aqua.impl;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * Created by krr428 on 7/10/14.
 */
public class FishBuilder
{
    private static FishBuilder instance = null;

    public static FishBuilder getInstance()
    {
        if (instance == null)
        {
            instance = new FishBuilder();
        }
        return instance;
    }


    private Random rand = new Random();

    private BufferedImage createTestImage(Color color)
    {
        BufferedImage testImage = new BufferedImage(48, 48, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = testImage.createGraphics();
        g2d.setColor(color);
        g2d.fillRect(0, 0, 48, 48);
        return testImage;
    }



    public AbstractAquariumObject createRandomFish(int maxX, int maxY)
    {
        int x = rand.nextInt(maxX - 48);
        int y = rand.nextInt(maxY - 48);

        System.out.println("New fish at (" + x + ", " + y + ")");

        return new TestFish(new Point2D.Double(x, y), createTestImage(Color.GREEN));
    }
}
