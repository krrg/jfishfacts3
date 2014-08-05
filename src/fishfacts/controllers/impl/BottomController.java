package fishfacts.controllers.impl;

import fishfacts.controllers.AbstractController;
import fishfacts.controllers.IBottomController;
import fishfacts.model.GameState;
import fishfacts.model.IGameModel;
import fishfacts.views.IBottomView;

/**
 * Created by krr428 on 8/5/14.
 */
public class BottomController extends AbstractController implements IBottomController
{
    private IBottomView view = null;

    public BottomController(IGameModel model, IBottomView view)
    {
        super(model);
        this.view = view;
    }

    @Override
    public void stateChanged(GameState newState) {
        if (newState == GameState.ACTIVE_GAME_PENDING)
        {
            this.view.showAnswerView();
        }
        else if (newState == GameState.POST_ACTIVE_GAME)
        {
            this.view.showStartView();
        }
    }
}
