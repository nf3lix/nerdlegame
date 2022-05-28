package de.dhbw.nerdlegame;

public interface GameStateObservable {
    void addGameStateChangedListener(GameStateObserver observer);
    void notifyGameStateObservers();
}
