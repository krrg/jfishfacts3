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
        internalPanel.setLayout(new GridLayout(1, 2));

        timeBar = new JProgressBar();
        fishBar = new JProgressBar();

        internalPanel.add(timeBar);
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
