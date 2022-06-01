package de.dhbw.nerdlegame.game;

import de.dhbw.nerdlegame.guess.GuessResult;
import de.dhbw.nerdlegame.player.Player;

public interface OnPlayerGuessObserver {
    void onPlayerGuess(Player player, GuessResult guess);
}
