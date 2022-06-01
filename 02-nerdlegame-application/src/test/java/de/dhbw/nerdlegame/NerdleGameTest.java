package de.dhbw.nerdlegame;

import de.dhbw.nerdlegame.calculation.Calculation;
import de.dhbw.nerdlegame.guess.Guess;
import de.dhbw.nerdlegame.listeners.OnWinObserver;
import de.dhbw.nerdlegame.player.Player;
import de.dhbw.nerdlegame.player.PlayerId;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class NerdleGameTest {

    @Test
    public void throwExceptionOnRegisteringPlayerIfGameIsNotWaitingForPlayers() {
        final NerdleGame nerdleGame = new NerdleGame(calculationGenerator(), gameTimer());
        registerAllPlayers(nerdleGame);
        assertThrows(GameStateException.class, () -> nerdleGame.registerPlayer(mock(Player.class)));
    }

    @Test
    public void throwExceptionOnGuessingIfGameWaitsForPlayers() {
        final NerdleGame nerdleGame = new NerdleGame(calculationGenerator(), gameTimer());
        assertThrows(GameStateException.class, () -> nerdleGame.makeGuess(mock(Guess.class)));
        nerdleGame.registerPlayer(mock(Player.class));
        assertThrows(GameStateException.class, () -> nerdleGame.makeGuess(mock(Guess.class)));
    }

    @Test
    public void generateCalculationOnConstructGame() {
        final CalculationGenerator generator = calculationGenerator();
        new NerdleGame(generator, gameTimer());
        verify(generator).nextCalculation();
    }

    @Test
    public void notifyWinnerDeterminedListener() {
        final String calculation = "12+35=47";
        final NerdleGame nerdleGame = new NerdleGame(calculationGenerator(calculation), gameTimer());
        final OnWinObserver observer = mock(OnWinObserver.class);
        nerdleGame.addOnWinObserver(observer);
        final Player player = playerWithId("cb500c98-c898-45be-b4e2-df826cb55653");
        nerdleGame.registerPlayer(player);
        for(int playerCount = 0; playerCount < NerdleGame.MAX_PLAYERS - 1; playerCount++) {
            nerdleGame.registerPlayer(mock(Player.class));
        }
        final Guess guess = mock(Guess.class);
        when(guess.calculation()).thenReturn(new Calculation(calculation));
        when(guess.player()).thenReturn(player);
        nerdleGame.makeGuess(guess);
        verify(observer).onWinnerDetermined(player);
    }

    @Test
    public void throwExceptionWhenTimeSinceLastGuessIsTooShort() {
        final String calculation = "12+35=47";
        final GameTimer gameTimer = mock(GameTimer.class);
        when(gameTimer.currentTimeInMillis()).thenReturn((long) NerdleGame.MIN_TIME_BETWEEN_GUESSES - 1);
        final NerdleGame nerdleGame = new NerdleGame(calculationGenerator(calculation), gameTimer);
        final Player player = playerWithId("cb500c98-c898-45be-b4e2-df826cb55653");
        nerdleGame.registerPlayer(player);
        for(int playerCount = 0; playerCount < NerdleGame.MAX_PLAYERS - 1; playerCount++) {
            nerdleGame.registerPlayer(mock(Player.class));
        }
        final Guess guess = mock(Guess.class);
        when(guess.player()).thenReturn(player);
        assertThrows(TooLittleTimeSinceLastGuess.class, () -> nerdleGame.makeGuess(guess));
    }

    private void registerAllPlayers(final NerdleGame nerdleGame) {
        for(int playerCount = 0; playerCount < NerdleGame.MAX_PLAYERS; playerCount++) {
            nerdleGame.registerPlayer(mock(Player.class));
        }
    }

    private Player playerWithId(final String id) {
        final Player player = mock(Player.class);
        when(player.playerId()).thenReturn(new PlayerId(id));
        return player;
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

    private GameTimer gameTimer() {
        final GameTimer timer = mock(GameTimer.class);
        when(timer.currentTimeInMillis()).thenReturn(1600000000000L);
        return timer;
    }

}
