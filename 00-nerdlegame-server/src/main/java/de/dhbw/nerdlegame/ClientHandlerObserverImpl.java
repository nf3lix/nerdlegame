package de.dhbw.nerdlegame;

import de.dhbw.nerdlegame.calculation.Calculation;
import de.dhbw.nerdlegame.guess.Guess;
import de.dhbw.nerdlegame.player.Player;
import de.dhbw.nerdlegame.player.PlayerId;
import de.dhbw.nerdlegame.player.PlayerName;

import java.util.UUID;

public class ClientHandlerObserverImpl implements ClientHandlerObserver {

    private NerdleGame nerdleGame;

    public ClientHandlerObserverImpl(final NerdleGame nerdleGame) {
        this.nerdleGame = nerdleGame;
    }

    @Override
    public void onGuess(final String calculation) {
        NerdleGameServer.logMessage("Player made a guess");
        nerdleGame.makeGuess(new Guess(UUID.randomUUID(), new Player(new PlayerId(UUID.randomUUID()), new PlayerName("Test")), new Calculation(calculation)));
    }

}
