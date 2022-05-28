package de.dhbw.nerdlegame;

import de.dhbw.nerdlegame.player.Player;

public interface DetermineWinnerObserver {
    void onWinnerDetermined(Player player);
}
