package fishfacts.model.aqua.impl;

import fishfacts.model.aqua.IAquariumObject;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

/**
 * Created by krr428 on 7/3/14.
 */
public abstract class AbstractAquariumObject implements IAquariumObject
{
    private Point2D location = null;
    private BufferedImage cachedScaledImage = null;

    public AbstractAquariumObject(Point2D location, BufferedImage image)
    {
        this(location.getX(), location.getY(), 1.0, image);
    }

    public AbstractAquariumObject(double x, double y, double scaling, BufferedImage image)
    {
        this.location = new Point2D.Double(x, y);
        if (image != null)
        {
            this.setImage(scaling, image);
        }
        else
        {
            this.cachedScaledImage = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        }
    }

    protected final void setImage(double scaling, BufferedImage image)
    {
        BufferedImage buffer = new BufferedImage(
                (int)(image.getWidth() * scaling),
                (int)(image.getHeight() * scaling),
                BufferedImage.TYPE_INT_ARGB
        );

        Graphics2D g2d = buffer.createGraphics();
        g2d.drawImage(image, AffineTransform.getScaleInstance(scaling, scaling), null);

        this.cachedScaledImage = buffer;
    }

    @Override
    public double getX()
    {
        return location.getX();
    }

    @Override
    public double getY()
    {
        return location.getY();
    }

    @Override
    public double getHeight()
    {
        return getImage().getHeight();
    }

    @Override
    public double getWidth()
    {
        return getImage().getWidth();
    }

    @Override
    public Rectangle2D getRectangle()
    {
        return new Rectangle2D.Double(getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public BufferedImage getImage()
    {
        return cachedScaledImage;
    }

    protected void setX(double x)
    {
        this.location.setLocation(x, location.getY());
    }

    protected void setY(double y)
    {
        this.location.setLocation(location.getX(), y);
    }
}

