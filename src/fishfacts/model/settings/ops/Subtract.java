package fishfacts.model.settings.ops;

/**
 * Created by krr428 on 7/3/14.
 */
public class Subtract extends AbstractOperator<Integer>
{
    private static Subtract instance = null;

    public static Subtract getInstance()
    {
        if (instance == null)
        {
            instance = new Subtract();
        }
        return instance;
    }

    @Override
    public char getSymbol()
    {
        return '-';
    }

    @Override
    public String getName()
    {
        return "Subtract";
    }

    @Override
    public ProblemTuple createProblemFrom(Integer t1, Integer t2)
    {
        return new ProblemTuple<>(t2 + t1, t1, t2, this);
    }
}
