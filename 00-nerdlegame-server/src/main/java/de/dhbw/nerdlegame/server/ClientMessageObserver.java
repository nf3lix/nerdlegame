package de.dhbw.nerdlegame.server;

public interface ClientMessageObserver {
    void onClientMessageReceived(String message);
}
