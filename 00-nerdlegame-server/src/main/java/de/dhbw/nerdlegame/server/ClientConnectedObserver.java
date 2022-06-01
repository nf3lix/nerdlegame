package de.dhbw.nerdlegame.server;

import de.dhbw.nerdlegame.server.ClientHandler;

public interface ClientConnectedObserver {
    void onClientConnected(ClientHandler clientHandler);
}
