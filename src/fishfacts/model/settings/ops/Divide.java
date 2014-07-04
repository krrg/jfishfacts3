package fishfacts.model.settings.ops;

/**
 * Created by krr428 on 7/3/14.
 */
public class Divide extends AbstractOperator<Integer>
{
    @Override
    public char getSymbol()
    {
        return '/';
    }

    @Override
    public String getName()
    {
        return "Divide";
    }

    @Override
    public ProblemTuple<Integer> createProblemFrom(Integer t1, Integer t2)
    {
        return new ProblemTuple<>(t1 * t2, t1, t2);
    }
}
