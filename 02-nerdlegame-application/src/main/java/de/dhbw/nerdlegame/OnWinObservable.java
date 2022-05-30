package de.dhbw.nerdlegame;

import de.dhbw.nerdlegame.player.Player;

public interface OnWinObservable {
    void addOnWinObserver(OnWinObserver observer);
    void notifyOnWinObservers(Player player);
}
