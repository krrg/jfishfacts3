package fishfacts.views.impl;

import fishfacts.controllers.IStartButtonController;
import fishfacts.views.IStartButtonView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by krr428 on 7/29/14.
 */
public class StartButtonView extends JPanel implements IStartButtonView
{
    private JButton btnStart = null;
    private IStartButtonController controller = null;

    public StartButtonView()
    {
        initComponents();
    }

    private void initComponents()
    {
        this.setLayout(new BorderLayout());

        btnStart = new JButton("Start Game");
        btnStart.setFont(btnStart.getFont().deriveFont(48.0F));
        this.add(btnStart);

        btnStart.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (controller != null)
                {
                    controller.handleGameStart();
                }
            }
        });
    }

    @Override
    public void enableStartButton()
    {
        btnStart.setEnabled(true);
    }

    @Override
    public void disableStartButton()
    {
        btnStart.setEnabled(false);
    }

    @Override
    public void setController(IStartButtonController controller)
    {
        this.controller = controller;
    }
}
