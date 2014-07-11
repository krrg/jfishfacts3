package fishfacts.views;

import fishfacts.controllers.IAnswerController;

/**
 * Created by krr428 on 7/5/14.
 */
public interface IAnswerView
{
    public void clearFields();

    public void setTermA(String termA);

    public void setTermB(String termB);

    public void setOperator(String operator);

    public void focusAnswerField();

    public void freezeView();

    public void unfreezeView();

    public void setAnswerController(IAnswerController controller);
}
