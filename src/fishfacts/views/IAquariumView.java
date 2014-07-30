package fishfacts.views;

import fishfacts.controllers.IAquariumController;
import fishfacts.controllers.impl.AquariumController;

import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

/**
 * Created by krr428 on 7/5/14.
 */
public interface IAquariumView extends IView<IAquariumController>
{
    public void updateBuffer(BufferedImage additional);

    public void requestRedraw();

}
