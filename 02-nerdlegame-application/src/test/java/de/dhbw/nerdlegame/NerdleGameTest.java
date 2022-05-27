package de.dhbw.nerdlegame;

import de.dhbw.nerdlegame.calculation.Calculation;
import de.dhbw.nerdlegame.guess.Guess;
import de.dhbw.nerdlegame.player.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class NerdleGameTest {

    @Test
    public void throwExceptionOnRegisteringPlayerIfGameIsNotWaitingForPlayers() {
        final NerdleGame nerdleGame = new NerdleGame(calculationGenerator());
        registerAllPlayers(nerdleGame);
        assertThrows(GameStateException.class, () -> nerdleGame.registerPlayer(mock(Player.class)));
    }

    @Test
    public void throwExceptionOnGuessingIfGameWaitsForPlayers() {
        final NerdleGame nerdleGame = new NerdleGame(calculationGenerator());
        assertThrows(GameStateException.class, () -> nerdleGame.makeGuess(mock(Guess.class)));
        nerdleGame.registerPlayer(mock(Player.class));
        assertThrows(GameStateException.class, () -> nerdleGame.makeGuess(mock(Guess.class)));
    }

    @Test
    public void generateCalculationOnConstructGame() {
        final CalculationGenerator generator = calculationGenerator();
        new NerdleGame(generator);
        verify(generator).nextCalculation();
    }

    private void registerAllPlayers(final NerdleGame nerdleGame) {
        for(int playerCount = 0; playerCount < NerdleGame.MAX_PLAYERS; playerCount++) {
            nerdleGame.registerPlayer(mock(Player.class));
        }
    }

    private CalculationGenerator calculationGenerator() {
        final CalculationGenerator generator = mock(CalculationGenerator.class);
        when(generator.nextCalculation()).thenReturn(new Calculation("12+35=47"));
        return generator;
    }

}
