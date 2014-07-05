package fishfacts.model;

/**
 * Created by krr428 on 7/5/14.
 */
public enum GameState
{
    START_SCREEN, //When the start screen is displaying, and the start button hasn't been pushed.
    PRE_START, // When the start button has been pushed
    ACTIVE_GAME_PENDING,
    ACTIVE_GAME_WRONG_ANSWER,
    ACTIVE_GAME_CORRECT_ANSWER,
    POST_GAME
}

