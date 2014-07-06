package fishfacts.model.settings;

/**
 * Created by krr428 on 7/3/14.
 */
public interface IGameSettings<T> extends IProblemSettings<T>
{
    public void setRepeatIncorrect(boolean repeat);

    public boolean getRepeatIncorrect();

    public void setIncorrectTimeout(int ms);

    public int getIncorrectTimeout();

    public void setTotalGameTime(int seconds);

    public int getTotalGameTime();

    public void setTankCapacity(int capacity);

    public int getTankCapacity();

    public void setCorrectPerFish(int correctPerFish);

    public int getCorrectPerFish();
}
