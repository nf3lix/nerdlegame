package de.dhbw.nerdlegame.server;

import de.dhbw.nerdlegame.message.Message;

public interface Receiver {
    void sendMessage(Message message);
}
