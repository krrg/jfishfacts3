package fishfacts.model.aqua.impl;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

/**
 * Created by krr428 on 8/23/14.
 */
public class BidirectionalFish extends AbstractAquariumObject
{
    private double dirCoeff = 0.25;
    private BufferedImage eastFace = null;
    private AffineTransform reverseX = null;

    public BidirectionalFish(Point2D p2d, BufferedImage eastFace, double dirCoeff)
    {
        super(p2d);
        this.eastFace = eastFace;
        initTransform();
        this.setDirCoeff(dirCoeff);
    }

    private void initTransform()
    {
        reverseX = new AffineTransform();
        reverseX.translate(eastFace.getWidth(), 0);
        reverseX.scale(-1, 1);
    }

    private void setDirCoeff(double dirCoeff)
    {
        this.dirCoeff = dirCoeff;
        if (dirCoeff < 0)
        {
            setImage(1.0, getWestFace());
        }
        else
        {
            setImage(1.0, eastFace);
        }
    }

    private BufferedImage getWestFace()
    {
        BufferedImage bufferedImage = new BufferedImage(eastFace.getWidth(), eastFace.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bufferedImage.createGraphics();
        g2d.transform(reverseX);
        g2d.drawImage(eastFace, 0, 0, eastFace.getWidth(), eastFace.getHeight(), null);
        return bufferedImage;
    }

    @Override
    public void calculateNewPosition(int ms)
    {
        this.setX(getX() + (ms * dirCoeff));
    }

    @Override
    public void handleAtEdge()
    {
        this.setDirCoeff(dirCoeff * -1.0);
    }
}
