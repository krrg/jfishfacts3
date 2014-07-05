package fishfacts.controllers.impl;

import fishfacts.controllers.IAnswerController;
import fishfacts.model.aqua.IAquarium;
import fishfacts.model.settings.IGameSettings;

/**
 * Created by krr428 on 7/5/14.
 */
public class AnswerController implements IAnswerController
{
    private IGameSettings<Integer> gameSettings = null;

    public AnswerController(IGameSettings<Integer> settings, IAquarium aquarium)
    {
        this.gameSettings = settings;
    }

    @Override
    public String getTermA()
    {
        return null;
    }

    @Override
    public String getTermB()
    {
        return null;
    }

    @Override
    public String getOperator()
    {
        return null;
    }

    @Override
    public void handleAnswer(String userAnswer)
    {

    }
}
