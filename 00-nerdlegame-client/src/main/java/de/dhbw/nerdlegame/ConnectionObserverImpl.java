package de.dhbw.nerdlegame;

import de.dhbw.nerdlegame.message.Message;
import de.dhbw.nerdlegame.message.MessageType;
import de.dhbw.nerdlegame.response_action.*;
import de.dhbw.nerdlegame.ui.MainWindow;

import java.util.HashMap;
import java.util.Map;

import static de.dhbw.nerdlegame.message.MessageType.*;

public class ConnectionObserverImpl implements ConnectionObserver {

    private final Map<MessageType, OnResponseAction> actions = new HashMap<>();

    public ConnectionObserverImpl(final MainWindow mainWindow) {
        actions.put(PLAYER_WINS, new DisplayPlayerWins(mainWindow));
        actions.put(GAME_STARTS, new DisplayGameStarts(mainWindow));
        actions.put(GAME_STATE_CHANGED, new DisplayGameStateChanged(mainWindow));
        actions.put(GUESS_RESULT, new DisplayGuessResult(mainWindow));
        actions.put(TOO_LITTLE_TIME_SINCE_LAST_GUESS_ERROR, new DisplayErrorMessage(mainWindow));
        actions.put(GUESSING_NOT_STARTED_YET, new DisplayGuessingNotStartedYet(mainWindow));
        actions.put(NO_MORE_GUESSES_AVAILABLE, new DisplayNoMoreGuessesAvailable(mainWindow));
    }

    @Override
    public void onMessageReceived(String content) {
        final Message message = new Message(content);
        final OnResponseAction action = actions.get(message.getMessageType());
        if(action == null) {
            new DisplayUnknownMessageType().run(message);
            return;
        }
        action.run(message);
    }

}
