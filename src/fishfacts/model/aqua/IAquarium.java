package fishfacts.model.aqua;

import java.awt.image.BufferedImage;

/**
 * Created by krr428 on 7/3/14.
 */
public interface IAquarium
{
    public BufferedImage getTankImage();

    public void calculateNewPositions(int ms);

    public void addObject(IAquariumObject object);

    public void clearAllObjects();

    /**
     * Defines the point at which for which, when new positions
     * @param pos
     */
    public void setRightEdge(int pos);

    public void setBottomEdge(int pos);

}
