package fishfacts.views;

import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

/**
 * Created by krr428 on 7/5/14.
 */
public interface IAquariumView
{
    public void updateBuffer(BufferedImage additional);

    public void requestRedraw();

}
