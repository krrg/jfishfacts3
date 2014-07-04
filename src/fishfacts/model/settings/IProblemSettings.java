package fishfacts.model.settings;

import fishfacts.model.settings.ops.AbstractOperator;

import java.util.Collection;
import java.util.Set;

/**
 * Created by krr428 on 7/3/14.
 */
public interface IProblemSettings
{
    public void setAllowedOperations(Collection<AbstractOperator> allowed);

    public Set<AbstractOperator> getAllowedOperations();

    public <T> Collection<T> getAllowedOperandsFor(AbstractOperator operator, int operandIndex);
}
