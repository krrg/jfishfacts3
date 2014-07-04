package fishfacts.model.settings;

import fishfacts.model.settings.ops.AbstractOperator;

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

    public Set<AbstractOperator> getAllowedOperations();

    public Collection<T> getAllowedOperandsFor(AbstractOperator<T> operator, int operandIndex);

}
