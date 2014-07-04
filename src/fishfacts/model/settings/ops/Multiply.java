package fishfacts.model.settings.ops;

/**
 * Created by krr428 on 7/3/14.
 */
public class Multiply extends AbstractOperator<Integer>
{
    private static Multiply instance = null;

    public static Multiply getInstance()
    {
        if (instance == null)
        {
            instance = new Multiply();
        }
        return instance;
    }

    private Multiply()
    {

    }

    @Override
    public char getSymbol()
    {
        return 'X';
    }

    @Override
    public String getName()
    {
        return "Multiply";
    }

    @Override
    public ProblemTuple createProblemFrom(Integer t1, Integer t2)
    {
        return new ProblemTuple<>(t1, t2, t1 * t2);
    }
}
