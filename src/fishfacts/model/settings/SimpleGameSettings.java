package fishfacts.model.settings;

import fishfacts.model.settings.ops.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Created by krr428 on 7/4/14.
 */
public class SimpleGameSettings implements IGameSettings<Integer>
{
    private boolean repeatIncorrect = false;
    private int incorrectTimeout = 2750;
    private int tankCapacity = 6;
    private int correctPerFish = 2;
    private Collection<AbstractOperator<Integer>> getAllowedOperators = null;

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

    }

    @Override
    public void addAllowedOperation(AbstractOperator<Integer> allowed)
    {

    }

    @Override
    public void removeAllowedOperation(AbstractOperator<Integer> disallowed)
    {

    }

    @Override
    public Set<AbstractOperator> getAllowedOperations()
    {
        return null;
    }

    @Override
    public Collection<Integer> getAllowedOperandsFor(AbstractOperator<Integer> operator, int operandIndex)
    {
        return null;
    }
}
