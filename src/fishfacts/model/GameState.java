package fishfacts.model;

/**
 * Created by krr428 on 7/5/14.
 */
public enum GameState
{
    START_SCREEN, //When the start screen is displaying, and the start button hasn't been pushed.
    PRE_START, // When the start button has been pushed
    ACTIVE_GAME_PENDING, //When we are waiting for the user to input an answer
    ACTIVE_GAME_WRONG_ANSWER, //When the wrong answer has been given
    ACTIVE_GAME_CORRECT_ANSWER, //When the correct answer has been given
    POST_ACTIVE_GAME_WON,
    POST_ACTIVE_GAME_LOST,
    POST_ACTIVE_GAME // For any cleanup that needs to happen, regardless of win or loss.
}

