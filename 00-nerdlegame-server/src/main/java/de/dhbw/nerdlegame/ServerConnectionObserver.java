package de.dhbw.nerdlegame;

import de.dhbw.nerdlegame.guess.Guess;
import de.dhbw.nerdlegame.player.Player;

public interface ServerConnectionObserver {
    void onPlayerJoined(Player player);
    void onGuess(Receiver receiver, Guess guess);
}
