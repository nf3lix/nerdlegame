package de.dhbw.nerdlegame.listeners;

import de.dhbw.nerdlegame.player.Player;

public interface OnWinObservable {
    void addOnWinObserver(OnWinObserver observer);
    void notifyOnWinObservers(Player player);
}
