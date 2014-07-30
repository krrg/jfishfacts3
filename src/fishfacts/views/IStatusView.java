package fishfacts.views;

import fishfacts.controllers.IStartButtonController;

/**
 * Created by krr428 on 7/5/14.
 */
public interface IStatusView
{
    public void resetView();

    public void setTime(int timeLeft, int totalTime);

    public void setFish(int newFishCount, int tankCapacity);
}
