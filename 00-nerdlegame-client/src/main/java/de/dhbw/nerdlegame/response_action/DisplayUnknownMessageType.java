package de.dhbw.nerdlegame.response_action;

import de.dhbw.nerdlegame.message.Message;

public class DisplayUnknownMessageType implements OnResponseAction {

    @Override
    public void run(final Message message) {
        System.out.println("Server sent message of unknown type: " + message.getContent());
    }

}
