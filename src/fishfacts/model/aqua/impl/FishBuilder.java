package fishfacts.model.aqua.impl;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;
import java.util.List;

/**
 * Created by krr428 on 7/10/14.
 */
public class FishBuilder
{
    private static FishBuilder instance = null;

    private FishBuilder()
    {
        initImages();
    }

    public static FishBuilder getInstance()
    {
        if (instance == null)
        {
            instance = new FishBuilder();
        }
        return instance;
    }


    private Random rand = new Random();

    private Map<String, BufferedImage> imageCache = new HashMap<>();

    private void initImages()
    {
        imageCache.put("bigfin", tryReadImage("bigfinfish_east.png"));
        imageCache.put("bottomfish", tryReadImage("bottomfish_east.png"));
        imageCache.put("fish8", tryReadImage("fish8_east.png"));
        imageCache.put("redfish", tryReadImage("redfish_east.png"));
        imageCache.put("seahorse", tryReadImage("seahorse_east.png"));
    }

    private BufferedImage tryReadImage(String resFileName)
    {
        try
        {
            return ImageIO.read(FishBuilder.class.getResourceAsStream("res/" + resFileName));
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

    public String getRandImageKey()
    {
        List<String> availableKeys = new ArrayList<>(imageCache.keySet());
        String randKey = availableKeys.get(rand.nextInt(availableKeys.size()));
        return randKey;
    }

    public double getRandCoefficientFor(String key)
    {
        double randCoeff = rand.nextDouble() * 0.15 + 0.1;
        if (key.equals("bottomfish"))
        {
            randCoeff *= 0.70;
        }
        return randCoeff;
    }

    public AbstractAquariumObject createRandomFish(int maxX, int maxY)
    {
        String randKey = getRandImageKey();
        double dirCoeff = getRandCoefficientFor(randKey);
        BufferedImage fishImage = imageCache.get(randKey);

        int x = rand.nextInt(maxX - fishImage.getWidth());
        int y = rand.nextInt(maxY - fishImage.getHeight());

        return new BidirectionalFish(new Point2D.Double(x, y), fishImage, dirCoeff);
    }




}
