package de.dhbw.nerdlegame;

import de.dhbw.nerdlegame.server.ClientHandler;

public interface ClientConnectedObserver {
    void onClientConnected(ClientHandler clientHandler);
}
