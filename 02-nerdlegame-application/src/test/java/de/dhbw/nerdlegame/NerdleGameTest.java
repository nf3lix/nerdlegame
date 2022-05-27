package de.dhbw.nerdlegame;

import de.dhbw.nerdlegame.player.Player;
import de.dhbw.nerdlegame.player.PlayerId;
import de.dhbw.nerdlegame.player.PlayerName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class NerdleGameTest {

    @Test
    public void throwExceptionOnRegisteringPlayerIfGameIsNotWaitingForPlayers() {
        final NerdleGame nerdleGame = new NerdleGame();
        nerdleGame.registerPlayer(new Player(new PlayerId("e4ccc47e-8f3f-48c5-8bf2-a861936b9664"), new PlayerName("TEST")));
        nerdleGame.registerPlayer(new Player(new PlayerId("e4ccc47e-8f3f-48c5-8bf2-a861936b9664"), new PlayerName("TEST")));
        assertThrows(GameStateException.class, () -> nerdleGame.registerPlayer(new Player(new PlayerId("e4ccc47e-8f3f-48c5-8bf2-a861936b9664"), new PlayerName("TEST"))));
    }

}
