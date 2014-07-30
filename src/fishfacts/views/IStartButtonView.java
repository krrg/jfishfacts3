package fishfacts.views;

import fishfacts.controllers.IStartButtonController;

import java.awt.event.ActionListener;

/**
 * Created by krr428 on 7/5/14.
 */
public interface IStartButtonView extends IView<IStartButtonController>
{
    public void enableStartButton();

    public void disableStartButton();
}
