package fishfacts.views.impl;

import fishfacts.controllers.IAnswerController;
import fishfacts.controllers.impl.AnswerController;
import fishfacts.views.IAnswerView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by Ken on 7/25/2014.
 */
public class AnswerView extends JPanel implements IAnswerView
{
    private JLabel lblFirstTerm = null;
    private JLabel lblOperator = null;
    private JLabel lblSecondTerm = null;
    private JLabel lblEquals = null;
    private JTextField txtAnswer = null;
    private Font defaultFont = null;
    private IAnswerController controller = null;

    public AnswerView()
    {
        initComponents();
    }

    private void initComponents()
    {
        initDefaultFont();
        initLabels();
        initAnswerHandler();
        initComponentLayout();
    }

    private void initComponentLayout()
    {
        this.setLayout(new FlowLayout());
        this.add(lblFirstTerm);
        this.add(lblOperator);
        this.add(lblSecondTerm);
        this.add(lblEquals);
        this.add(txtAnswer);
    }

    private void initDefaultFont()
    {
        final float SIZE_COEFFICIENT = 72.0F;
        defaultFont = new JLabel().getFont().deriveFont(SIZE_COEFFICIENT);
    }

    private void initLabels()
    {
        lblFirstTerm = new JLabel("a");
        lblOperator = new JLabel("+");
        lblSecondTerm = new JLabel("b");
        lblEquals = new JLabel(" = ");

        lblFirstTerm.setFont(defaultFont);
        lblOperator.setFont(defaultFont);
        lblSecondTerm.setFont(defaultFont);
        lblEquals.setFont(defaultFont);
    }

    private void initAnswerHandler()
    {
        txtAnswer = new JTextField(2);
        txtAnswer.setFont(defaultFont);
        txtAnswer.addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyPressed(KeyEvent e)
            {
                if (e.getKeyCode() == KeyEvent.VK_ENTER)
                {
                    System.out.println("Enter key pressed, handing off to controller.");
                    controller.handleAnswer(txtAnswer.getText());
                }
            }
        });
    }

    @Override
    public void clearFields()
    {
        lblFirstTerm.setText("");
        lblSecondTerm.setText("");
        lblOperator.setText("");
        txtAnswer.setText("");
    }

    @Override
    public void setTermA(String termA)
    {
        lblFirstTerm.setText(termA);
    }

    @Override
    public void setTermB(String termB)
    {
        lblSecondTerm.setText(termB);
    }

    @Override
    public void setOperator(String operator)
    {
        lblOperator.setText(operator);
    }

    @Override
    public void setAnswerText(String answerText)
    {
        txtAnswer.setText(answerText);
    }

    @Override
    public void focusAnswerField()
    {
        txtAnswer.requestFocusInWindow();
    }

    @Override
    public void freezeView()
    {
        txtAnswer.setEnabled(false);
    }

    @Override
    public void unfreezeView()
    {
        txtAnswer.setEnabled(true);
    }

    @Override
    public void setController(IAnswerController controller)
    {
        this.controller = controller;
    }

//    public static void main(String[] args)
//    {
//        JFrame jf = new JFrame();
//        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        jf.add(new AnswerView());
//
//        jf.pack();
//        jf.setVisible(true);
//    }
}
