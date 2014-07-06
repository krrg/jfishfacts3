package fishfacts.controllers.impl;

import fishfacts.controllers.AbstractController;
import fishfacts.controllers.IStartButtonController;
import fishfacts.model.GameState;
import fishfacts.model.IGameModel;
import fishfacts.views.IStartButtonView;

/**
 * Created by krr428 on 7/5/14.
 */
public class StartButtonController extends AbstractController implements IStartButtonController
{
    private IStartButtonView view = null;

    public StartButtonController(IGameModel model, IStartButtonView view)
    {
        super(model);
        this.view = view;
    }

    @Override
    public void stateChanged(GameState newState)
    {
        if (newState == GameState.START_SCREEN)
        {
            view.enableStartButton();
        }
        else
        {
            view.disableStartButton();
        }
    }

    @Override
    public void handleGameStart()
    {
        if (getModel().getCurrentState() != GameState.START_SCREEN)
        {
            throw new IllegalStateException("Incorrect state to handle game start!" + getModel().getCurrentState());
        }

        getModel().requestStateChange(GameState.PRE_START);
    }
}
