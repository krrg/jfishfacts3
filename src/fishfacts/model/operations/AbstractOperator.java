package fishfacts.model.operations;

/**
 * Created by krr428 on 7/3/14.
 */
public abstract class AbstractOperator<T>
{
    public abstract char getSymbol();

    public abstract String getName();

    public abstract ProblemTuple<T> createProblemFrom(T t1, T t2);
}
