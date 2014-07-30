package fishfacts.views.impl;

import fishfacts.controllers.IAquariumController;
import fishfacts.controllers.impl.AquariumController;
import fishfacts.views.IAquariumView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;

/**
 * Created by krr428 on 7/25/14.
 */
public class AquariumView extends JPanel implements IAquariumView
{
    private BufferedImage buffer = null;
    private IAquariumController controller = null;

    public AquariumView()
    {
        this.addComponentListener(new ComponentAdapter()
        {
            @Override
            public void componentResized(ComponentEvent e)
            {
                if (controller != null)
                {
                    controller.handleResize(getSize());
                }
            }
        });

        this.setDoubleBuffered(true);
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        if (buffer != null)
        {
            g.drawImage(buffer, 0, 0, this);
        }

    }

    @Override
    public void updateBuffer(BufferedImage additional)
    {
        this.buffer = additional;
    }

    @Override
    public void requestRedraw()
    {
        this.repaint();
    }

    @Override
    public void setController(IAquariumController controller)
    {
        this.controller = controller;
    }
}
