package fishfacts.model.settings.ops;

/**
 * Created by krr428 on 7/3/14.
 */
public class Add extends AbstractOperator<Integer>
{
    private static Add instance = null;

    public static Add getInstance()
    {
        if (instance == null)
        {
            instance = new Add();
        }
        return instance;
    }

    private Add()
    {

    }

    @Override
    public char getSymbol()
    {
        return '+';
    }

    @Override
    public String getName()
    {
        return "Add";
    }

    @Override
    public ProblemTuple<Integer> createProblemFrom(Integer t1, Integer t2)
    {
        return new ProblemTuple<>(t1, t2, t1 + t2);
    }
}
