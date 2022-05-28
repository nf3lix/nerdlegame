package de.dhbw.nerdlegame;

import de.dhbw.nerdlegame.guess.Guess;
import de.dhbw.nerdlegame.player.Player;

public class ServerConnectionObserverImpl implements ServerConnectionObserver {

    private final NerdleGame game;

    public ServerConnectionObserverImpl(final NerdleGame game) {
        this.game = game;
    }

    @Override
    public void onPlayerJoined(final Player player) {
        game.registerPlayer(player);
    }

    @Override
    public void onGuess(Guess guess) {
        game.makeGuess(guess);
    }

}
