package fishfacts.views.impl;

import fishfacts.views.IStatusView;

import javax.swing.*;
import java.awt.*;

/**
 * Created by krr428 on 8/6/14.
 */
public class StatusView extends JPanel implements IStatusView
{
    private JProgressBar timeBar = null;
    private JProgressBar fishBar = null;


    public StatusView()
    {
        initComponents();
    }

    private void initComponents()
    {
        JPanel internalPanel = new JPanel();
        internalPanel.setLayout(new FlowLayout());

        timeBar = new JProgressBar();
        fishBar = new JProgressBar();

        JLabel lblTime = new JLabel("Time:");
        JLabel lblFish = new JLabel(" Fish:");

        lblTime.setFont(lblTime.getFont().deriveFont(16.0F));
        lblFish.setFont(lblFish.getFont().deriveFont(16.0F));
        timeBar.setFont(lblTime.getFont());
        fishBar.setFont(lblFish.getFont());

        internalPanel.add(lblTime);
        internalPanel.add(timeBar);
        internalPanel.add(lblFish);
        internalPanel.add(fishBar);

        this.add(internalPanel);
    }

    @Override
    public void resetView()
    {
        timeBar.setValue(0);
        fishBar.setValue(0);
    }

    @Override
    public void setTime(int timeLeft, int totalTime)
    {
        timeBar.setMaximum(totalTime);
        timeBar.setValue(timeLeft);
    }

    @Override
    public void setFish(int newFishCount, int tankCapacity)
    {
        fishBar.setMaximum(tankCapacity);
        fishBar.setValue(newFishCount);
    }
}
