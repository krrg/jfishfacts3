package fishfacts.model.aqua.impl;

import fishfacts.model.aqua.IAquarium;
import fishfacts.model.aqua.IAquariumObject;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by krr428 on 7/3/14.
 */
public class Aquarium implements IAquarium
{
    private int width;
    private int height;
    private List<IAquariumObject> aquariumObjects = null;

    public Aquarium(int width, int height)
    {
        this.width = width;
        this.height = height;
    }

    @Override
    public BufferedImage getTankImage()
    {
        BufferedImage buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = buffer.createGraphics();
        return buffer;
    }

    private Rectangle2D getBounds()
    {
        return new Rectangle2D.Double(0, 0, width, height);
    }

    private boolean isTouchingEdge(IAquariumObject object)
    {
        //Does the aquarium contain only part of the object?
        return ! this.getBounds().contains(object.getRectangle()) &&
                this.getBounds().intersects(object.getRectangle());
    }

    private boolean intersects(IAquariumObject object)
    {
        return this.getBounds().intersects(object.getRectangle());
    }

    @Override
    public void calculateNewPositions(int ms)
    {
        List<IAquariumObject> markedForDeletion = new ArrayList<>();
        for (IAquariumObject obj: aquariumObjects)
        {
            obj.calculateNewPosition(ms);
            if (isTouchingEdge(obj))
            {
                obj.handleAtEdge();
            }
            else if (! intersects(obj))
            {
                markedForDeletion.add(obj);
            }
        }

        aquariumObjects.removeAll(markedForDeletion);
    }

    @Override
    public void addObject(IAquariumObject object)
    {
        this.aquariumObjects.add(object);
    }

    @Override
    public void clearAllObjects()
    {
        this.aquariumObjects.clear();
    }

    @Override
    public void setWidth(int pos)
    {
        this.width = pos;
    }

    @Override
    public void setHeight(int pos)
    {
        this.height = pos;
    }

    @Override
    public int getWidth()
    {
        return width;
    }

    @Override
    public int getHeight()
    {
        return height;
    }

    @Override
    public Collection<IAquariumObject> getTankContents()
    {
        return Collections.unmodifiableCollection(aquariumObjects);
    }
}
