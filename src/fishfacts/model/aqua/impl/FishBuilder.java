package fishfacts.model.aqua.impl;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
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

    private BufferedImage createTestImage()
    {
        try
        {
            return ImageIO.read(FishBuilder.class.getResourceAsStream("res/redfish_east.png"));
        }
        catch(IOException e)
        {
            BufferedImage errorImage = new BufferedImage(48, 48, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = errorImage.createGraphics();
            g2d.setColor(new Color(141, 173, 54));
            g2d.fillOval(0, 0, 48, 48);
            return errorImage;
        }

    }

    public AbstractAquariumObject createRandomFish(int maxX, int maxY)
    {
        int x = rand.nextInt(maxX - 48);
        int y = rand.nextInt(maxY - 48);

        System.out.println("New fish at (" + x + ", " + y + ")");

        return new BidirectionalFish(new Point2D.Double(x, y), createTestImage(), 0.25);
    }




}
