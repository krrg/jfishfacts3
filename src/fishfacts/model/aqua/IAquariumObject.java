package fishfacts.model.aqua;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

/**
 * Created by krr428 on 7/3/14.
 */
public interface IAquariumObject
{
    public double getX();

    public double getY();

    public double getHeight();

    public double getWidth();

    public Rectangle2D getRectangle();

    public void calculateNewPosition(int ms);

    public void handleAtEdge();

    public BufferedImage getImage();

}
