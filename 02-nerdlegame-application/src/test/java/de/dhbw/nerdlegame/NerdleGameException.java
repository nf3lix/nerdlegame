package de.dhbw.nerdlegame;

import de.dhbw.nerdlegame.player.Player;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class NerdleGameException {

    @Test
    public void throwExceptionOnRegisteringPlayerIfGameIsNotWaitingForPlayers() {
        final NerdleGame nerdleGame = new NerdleGame();
        nerdleGame.registerPlayer(new Player(UUID.fromString("e4ccc47e-8f3f-48c5-8bf2-a861936b9664"), "TEST"));
        nerdleGame.registerPlayer(new Player(UUID.fromString("e4ccc47e-8f3f-48c5-8bf2-a861936b9664"), "TEST"));
        assertThrows(GameStateException.class, () -> nerdleGame.registerPlayer(new Player(UUID.fromString("e4ccc47e-8f3f-48c5-8bf2-a861936b9664"), "TEST")));
    }

}
