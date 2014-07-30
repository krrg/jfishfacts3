package fishfacts.model.aqua.impl;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by krr428 on 7/3/14.
 */
public class Bubble extends AbstractAquariumObject
{
    private static BufferedImage bubbleFullRes = null;
    private double speed = 1.0;

    public Bubble(double x, double y, double scaling, double speed)
    {
        super(x, y, scaling, null);
        this.speed = speed;
        initBubbleImage();
        super.setImage(scaling, bubbleFullRes);
    }

    private void initBubbleImage()
    {
        if (bubbleFullRes != null)
        {
            return;
        }
        try
        {
            bubbleFullRes = ImageIO.read(Bubble.class.getResource("res/bubble.png"));

        } catch (IOException ex)
        {
            System.out.println(ex);
            bubbleFullRes = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        }
    }

    @Override
    public void calculateNewPosition(int ms)
    {
        final double SPEED_COEFFICIENT = 0.125;
        this.setY(getY() - (speed * ms * SPEED_COEFFICIENT));
    }

    @Override
    public void handleAtEdge()
    {
        //We don't want to do anything, just let the bubble float off the edge and be deleted.
        return;
    }

}
