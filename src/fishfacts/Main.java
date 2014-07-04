package fishfacts;

import fishfacts.model.aqua.impl.Aquarium;
import fishfacts.model.aqua.impl.Bubble;

import javax.swing.*;
import java.awt.*;

/**
 * Created by krr428 on 7/3/14.
 */
public class Main
{
    public static void main(String[] args)
    {
        JFrame jf = new JFrame();
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel jp = new JPanel()
        {
            @Override
            protected void paintComponent(Graphics g)
            {
                super.paintComponent(g);

            }
        };

        jf.setSize(600, 600);
        jf.setVisible(true);
    }
}
