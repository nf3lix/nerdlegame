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

    @Test
    public void notifyWinnerDeterminedListener() {
        final String calculation = "12+35=47";
        final NerdleGame nerdleGame = new NerdleGame(calculationGenerator(calculation));
        final DetermineWinnerObserver observer = mock(DetermineWinnerObserver.class);
        nerdleGame.addWinnerDeterminedListener(observer);
        registerAllPlayers(nerdleGame);
        final Guess guess = mock(Guess.class);
        final Player player = mock(Player.class);
        when(guess.calculation()).thenReturn(new Calculation(calculation));
        when(guess.player()).thenReturn(player);
        nerdleGame.makeGuess(guess);
        verify(observer).onWinnerDetermined(player);
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

    private CalculationGenerator calculationGenerator(final String calculation) {
        final CalculationGenerator generator = mock(CalculationGenerator.class);
        when(generator.nextCalculation()).thenReturn(new Calculation(calculation));
        return generator;
    }

}
