package de.dhbw.nerdlegame;

import de.dhbw.nerdlegame.message.Message;

public interface Receiver {
    void sendMessage(Message message);
}
