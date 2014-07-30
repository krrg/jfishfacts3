package fishfacts.views;

import fishfacts.controllers.IAnswerController;

/**
 * Created by krr428 on 7/5/14.
 */
public interface IAnswerView extends IView<IAnswerController>
{
    public void clearFields();

    public void setTermA(String termA);

    public void setTermB(String termB);

    public void setAnswerText(String answerText);

    public void setOperator(String operator);

    public void focusAnswerField();

    public void freezeView();

    public void unfreezeView();

}
