package de.dhbw.nerdlegame.server;

public interface ClientConnectionClosedObservable {
    void addClientConnectionClosedListener(ClientConnectionClosedObserver observer);
    void notifyClientConnectionClosedObservers();
}
