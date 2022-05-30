package de.dhbw.nerdlegame;

import de.dhbw.nerdlegame.player.Player;

public interface OnWinObserver {
    void onWinnerDetermined(Player player);
}
