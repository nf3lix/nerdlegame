package de.dhbw.nerdlegame.guess;

import de.dhbw.nerdlegame.calculation.Calculation;
import de.dhbw.nerdlegame.player.Player;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

public class GuessTest {

    @Test
    public void matchesWithCalculationWhenCalculationsAreEqual() {
        final Player player = mock(Player.class);
        final UUID uuid = UUID.fromString("e4ccc47e-8f3f-48c5-8bf2-a861936b9664");
        final Calculation calculation1 = new Calculation("21+47=68");
        final Calculation calculation2 = new Calculation("21+47=68");
        final Guess guess = new Guess(uuid, player, calculation1);
        assertTrue(guess.matches(calculation2));
    }

}
