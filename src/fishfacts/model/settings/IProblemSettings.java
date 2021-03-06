package fishfacts.model.settings;

import fishfacts.model.operations.AbstractOperator;

import java.util.Collection;
import java.util.Set;

/**
 * Created by krr428 on 7/3/14.
 */
public interface IProblemSettings<T>
{
    public void setAllowedOperations(Collection<AbstractOperator<T>> allowed);

    public void addAllowedOperation(AbstractOperator<T> allowed);

    public void removeAllowedOperation(AbstractOperator<T> disallowed);

    public Set<AbstractOperator<T>> getAllowedOperations();

    public Collection<T> getAllowedOperandsFor(AbstractOperator<T> operator, int operandIndex);

    public void setAllowedOperandsFor(AbstractOperator<T> operator, int operandIndex, Collection<T> allowedOperands);

}
