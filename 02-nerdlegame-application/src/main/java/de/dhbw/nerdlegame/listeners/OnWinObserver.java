package de.dhbw.nerdlegame.listeners;

import de.dhbw.nerdlegame.player.Player;

public interface OnWinObserver {
    void onWinnerDetermined(Player player);
}
