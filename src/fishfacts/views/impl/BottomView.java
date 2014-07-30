package fishfacts.views.impl;

import fishfacts.views.IBottomView;

import javax.swing.*;
import java.awt.*;

/**
 * Created by krr428 on 7/29/14.
 */
public class BottomView extends JPanel implements IBottomView
{
    private JComponent startButtonView = null;
    private JComponent answerView = null;

    public BottomView(JComponent startButtonView, JComponent answerView)
    {
        this.startButtonView = startButtonView;
        this.answerView = answerView;
        initComponents();
    }

    private void initComponents()
    {
        this.setLayout(new CardLayout());
        this.add(startButtonView, "START");
        this.add(answerView, "ANSWER");
        ((CardLayout)this.getLayout()).show(this, "START");
    }

    @Override
    public void showAnswerView()
    {
        ((CardLayout)this.getLayout()).show(this, "ANSWER");
    }

    @Override
    public void showStartView()
    {
        ((CardLayout)this.getLayout()).show(this, "START");
    }
}
