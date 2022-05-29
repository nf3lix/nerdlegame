package de.dhbw.nerdlegame;

import de.dhbw.nerdlegame.server.ClientHandler;

public interface ClientConnectedObservable {
    void addClientConnectedListener(ClientConnectedObserver observer);
    void notifyClientConnectedListeners(ClientHandler clientHandler);
}
