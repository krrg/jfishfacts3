package fishfacts.model.settings.ops;

/**
 * Created by krr428 on 7/3/14.
 */
public abstract class AbstractOperator
{
    public abstract char getSymbol();

    public abstract String getName();

    public abstract <T> ProblemTuple createProblemFrom(T t1, T t2);
}
