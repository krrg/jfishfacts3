package fishfacts.model.aqua.impl;

import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

/**
 * Created by krr428 on 7/10/14.
 */
public class TestFish extends AbstractAquariumObject
{
    private double dirCoeff = 1.0;

    public TestFish(Point2D location, BufferedImage image)
    {
        super(location, image);
    }

    @Override
    public void calculateNewPosition(int ms)
    {
        this.setX(getX() + (ms * dirCoeff));
    }

    @Override
    public void handleAtEdge()
    {
        dirCoeff *= -1.0;
    }
}
