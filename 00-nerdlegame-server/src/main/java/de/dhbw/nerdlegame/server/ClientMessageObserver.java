package de.dhbw.nerdlegame.server;

import de.dhbw.nerdlegame.message.Message;

public interface ClientMessageObserver {
    void onClientMessageReceived(String message);
}
