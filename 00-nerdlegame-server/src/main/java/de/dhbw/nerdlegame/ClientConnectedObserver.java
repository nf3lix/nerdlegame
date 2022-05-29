package de.dhbw.nerdlegame;

import de.dhbw.nerdlegame.server.ClientHandler;

import java.net.Socket;

public interface ClientConnectedObserver {
    void onClientConnected(ClientHandler clientHandler, ServerConnectionObserver observer);
}
