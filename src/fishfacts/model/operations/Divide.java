package fishfacts.model.operations;

/**
 * Created by krr428 on 7/3/14.
 */
public class Divide extends AbstractOperator<Integer>
{
    private static Divide instance = null;

    public static Divide getInstance()
    {
        if (instance == null)
        {
            instance = new Divide();
        }
        return instance;
    }

    private Divide()
    {

    }

    @Override
    public char getSymbol()
    {
        return '\u00F7';
    }

    @Override
    public String getName()
    {
        return "Divide";
    }

    @Override
    public ProblemTuple<Integer> createProblemFrom(Integer t1, Integer t2)
    {
        return new ProblemTuple<>(t1 * t2, t1, t2, this);
    }
}
