package de.dhbw.nerdlegame.response_action;

import de.dhbw.nerdlegame.message.Message;

public class DisplayGameStateChanged implements OnResponseAction {

    @Override
    public void run(Message message) {
        System.out.println(message.getContent());
    }

}
