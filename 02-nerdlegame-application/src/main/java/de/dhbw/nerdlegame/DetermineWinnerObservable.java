package de.dhbw.nerdlegame;

import de.dhbw.nerdlegame.player.Player;

public interface DetermineWinnerObservable {
    void addWinnerDeterminedListener(DetermineWinnerObserver observer);
    void notifyWinnerDeterminedObservers(Player player);
}
