package fishfacts.model.settings.ops;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by krr428 on 7/3/14.
 */
public class ProblemTuple<T>
{
    private List<T> operands = null;
    private AbstractOperator<T> operator = null;

    public ProblemTuple(T a, T b, T c, AbstractOperator<T> operator)
    {
        this.operator = operator;
        this.operands = Arrays.asList(a, b, c);
    }

    public T getOperandA()
    {
        return operands.get(0);
    }

    public T getOperandB()
    {
        return operands.get(1);
    }

    public T getAnswer()
    {
        return operands.get(2);
    }

    public AbstractOperator<T> getOperator() { return operator; }

    public boolean isCorrect(T answer)
    {
        return getAnswer().equals(answer);
    }
}
