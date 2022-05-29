package de.dhbw.nerdlegame;

import de.dhbw.nerdlegame.message.Message;
import de.dhbw.nerdlegame.message.MessageType;
import de.dhbw.nerdlegame.response_action.DisplayGameStateChanged;
import de.dhbw.nerdlegame.response_action.DisplayGuessResult;
import de.dhbw.nerdlegame.response_action.OnResponseAction;

import java.util.HashMap;
import java.util.Map;

public class ConnectionObserverImpl implements ConnectionObserver {

    private final Map<MessageType, OnResponseAction> actions = new HashMap<>();

    public ConnectionObserverImpl() {
        actions.put(MessageType.PLAYER_WINS, new DisplayGameStateChanged());
        actions.put(MessageType.GUESS_RESULT, new DisplayGuessResult());
    }

    @Override
    public void onMessageReceived(String content) {
        final Message message = new Message(content);
        final OnResponseAction action = actions.get(message.getMessageType());
        action.run(message);
    }

}
