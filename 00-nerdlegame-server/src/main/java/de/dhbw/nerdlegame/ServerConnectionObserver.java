package de.dhbw.nerdlegame;

public interface ServerConnectionObserver {
    void onWaitingForClients();
    void onClientConnected();
}
