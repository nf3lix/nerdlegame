package de.dhbw.nerdlegame;

public interface CommandObservable {
    void addCommandListener(CommandObserver observer);
}
