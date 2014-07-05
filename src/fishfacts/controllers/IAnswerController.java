package fishfacts.controllers;

/**
 * Created by krr428 on 7/5/14.
 */
public interface IAnswerController
{
    public String getTermA();

    public String getTermB();

    public String getOperator();

    public void handleAnswer(String userAnswer);
}
