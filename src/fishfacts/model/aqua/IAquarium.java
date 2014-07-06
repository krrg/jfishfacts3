package fishfacts.model.aqua;

import java.awt.image.BufferedImage;
import java.util.Collection;

/**
 * Created by krr428 on 7/3/14.
 */
public interface IAquarium extends Iterable<IAquariumObject>
{
    public BufferedImage getTankImage();

    public void calculateNewPositions(int ms);

    public void addObject(IAquariumObject object);

    public void clearAllObjects();

    public Collection<IAquariumObject> getTankContents();

    /**
     * Defines the point at which for which, when new positions
     * @param pos
     */
    public void setWidth(int pos);

    public void setHeight(int pos);

    public int getWidth();

    public int getHeight();

}
