package de.dhbw.nerdlegame;

import de.dhbw.nerdlegame.guess.Guess;
import de.dhbw.nerdlegame.guess.GuessResult;
import de.dhbw.nerdlegame.message.Message;
import de.dhbw.nerdlegame.player.Player;
import de.dhbw.nerdlegame.resource.GuessResultResource;

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
    public void onGuess(final Receiver receiver, final Guess guess) {
        final GuessResult guessResult = game.makeGuess(guess);
        receiver.sendMessage(new Message("GUESS", new GuessResultResource(guessResult).toString()));
    }

}
