package de.dhbw.nerdlegame.server;

public interface ClientConnectedObservable {
    void addClientConnectedListener(ClientConnectedObserver observer);
    void notifyClientConnectedListeners(ClientHandler clientHandler);
}
