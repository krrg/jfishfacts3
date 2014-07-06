package fishfacts.views;

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
}
