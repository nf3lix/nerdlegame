package de.dhbw.nerdlegame;

import de.dhbw.nerdlegame.player.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

public class NerdleGameTest {

    @Test
    public void throwExceptionOnRegisteringPlayerIfGameIsNotWaitingForPlayers() {
        final NerdleGame nerdleGame = new NerdleGame();
        registerAllPlayers(nerdleGame);
        assertThrows(GameStateException.class, () -> nerdleGame.registerPlayer(mock(Player.class)));
    }

    private void registerAllPlayers(final NerdleGame nerdleGame) {
        for(int playerCount = 0; playerCount < NerdleGame.MAX_PLAYERS; playerCount++) {
            nerdleGame.registerPlayer(mock(Player.class));
        }
    }

}
