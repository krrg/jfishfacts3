package fishfacts.model.settings;

/**
 * Created by krr428 on 7/3/14.
 */
public interface IGameSettings extends IProblemSettings
{
    public void setRepeatIncorrect(boolean repeat);

    public boolean getRepeatIncorrect();

    public void setIncorrectTimeout(int ms);

    public int getIncorrectTimeout();

    public void setTankCapacity(int capacity);

    public int getTankCapacity();

    public void setCorrectPerFish(int correctPerFish);

    public int getCorrectPerFish();

    public void copySettingsFrom(IGameSettings other);
}
