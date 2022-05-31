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
        actions.put(PLAYER_WINS, new DisplayGameStateChanged());
        actions.put(GAME_STATE_CHANGED, new DisplayGameStateChanged());
        actions.put(GUESS_RESULT, new DisplayGuessResult(mainWindow));
        actions.put(TOO_LITTLE_TIME_SINCE_LAST_GUESS_ERROR, new DisplayErrorMessage());
        actions.put(GUESSING_NOT_STARTED_YET, new DisplayGuessingNotStartedYet());
    }

    @Override
    public void onMessageReceived(String content) {
        System.out.println(content);
        final Message message = new Message(content);
        final OnResponseAction action = actions.get(message.getMessageType());
        if(action == null) {
            new DisplayUnknownMessageType().run(message);
            return;
        }
        action.run(message);
    }

}
