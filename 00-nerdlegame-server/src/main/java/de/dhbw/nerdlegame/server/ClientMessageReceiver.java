package de.dhbw.nerdlegame.server;

public interface ClientMessageReceiver {
    void addClientMessageObserver(ClientMessageObserver observer);
    void notifyClientMessageObservers(String message);
}
