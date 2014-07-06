package fishfacts.controllers;

import fishfacts.model.IGameModel;
import fishfacts.model.IGameStateListener;

/**
 * Created by krr428 on 7/5/14.
 */
public abstract class AbstractController implements IGameStateListener
{
    private IGameModel model = null;

    public AbstractController(IGameModel model)
    {
        this.model = model;
        this.model.addGameStateListener(this);
    }

    protected IGameModel getModel()
    {
        return this.model;
    }
}
