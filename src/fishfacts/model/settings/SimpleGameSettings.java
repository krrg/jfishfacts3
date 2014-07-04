package fishfacts.model.settings;

import fishfacts.model.settings.ops.*;

import java.util.*;

/**
 * Created by krr428 on 7/4/14.
 */
public class SimpleGameSettings implements IGameSettings<Integer>
{
    private boolean repeatIncorrect = false;
    private int incorrectTimeout = 2750;
    private int tankCapacity = 6;
    private int correctPerFish = 2;
    private Set<AbstractOperator<Integer>> allowedOperators = null;
    private Map<AbstractOperator<Integer>, List<List<Integer>>> allowedOperands = null;

    public SimpleGameSettings()
    {
        allowedOperators = new HashSet<>();
        allowedOperands = new HashMap<>();
    }

    @Override
    public void setRepeatIncorrect(boolean repeat)
    {
        this.repeatIncorrect = repeat;
    }

    @Override
    public boolean getRepeatIncorrect()
    {
        return repeatIncorrect;
    }

    @Override
    public void setIncorrectTimeout(int ms)
    {
        this.incorrectTimeout = ms;
    }

    @Override
    public int getIncorrectTimeout()
    {
        return this.incorrectTimeout;
    }

    @Override
    public void setTankCapacity(int capacity)
    {
        this.tankCapacity = capacity;
    }

    @Override
    public int getTankCapacity()
    {
        return this.tankCapacity;
    }

    @Override
    public void setCorrectPerFish(int correctPerFish)
    {
        this.correctPerFish = correctPerFish;
    }

    @Override
    public int getCorrectPerFish()
    {
        return this.correctPerFish;
    }

    @Override
    public void setAllowedOperations(Collection<AbstractOperator<Integer>> allowed)
    {
        allowedOperators.clear();
        allowedOperators.addAll(allowed);
    }

    @Override
    public void addAllowedOperation(AbstractOperator<Integer> allowed)
    {
        allowedOperators.add(allowed);
    }

    @Override
    public void removeAllowedOperation(AbstractOperator<Integer> disallowed)
    {
        allowedOperators.remove(disallowed);
    }

    @Override
    public Set<AbstractOperator<Integer>> getAllowedOperations()
    {
        return Collections.unmodifiableSet(allowedOperators);
    }

    @Override
    public Collection<Integer> getAllowedOperandsFor(AbstractOperator<Integer> operator, int operandIndex)
    {
        if (! allowedOperands.containsKey(operator))
        {
            return Collections.EMPTY_LIST;
        }
        else
        {
            return allowedOperands.get(operator).get(operandIndex);
        }
    }

    @Override
    public void setAllowedOperandsFor(AbstractOperator<Integer> operator, int operandIndex, Collection<Integer> allowed)
    {
        if (! this.allowedOperands.containsKey(operator))
        {
            allowedOperands.put(operator, new ArrayList<List<Integer>>());
        }
        if (null == this.allowedOperands.get(operator).get(operandIndex))
        {
            allowedOperands.get(operator).set(operandIndex, new ArrayList<Integer>());
        }

        allowedOperands.get(operator).get(operandIndex).addAll(allowed);
    }
}
