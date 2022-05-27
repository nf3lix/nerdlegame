package de.dhbw.nerdlegame;

import de.dhbw.nerdlegame.calculation.Calculation;
import de.dhbw.nerdlegame.guess.Guess;
import de.dhbw.nerdlegame.guess.GuessResult;
import de.dhbw.nerdlegame.player.Player;

import java.util.ArrayList;
import java.util.List;

public class NerdleGame {

    public static final int MAX_PLAYERS = 2;
    private final List<Player> players = new ArrayList<>();
    private GameState gameState = GameState.WAIT_FOR_PLAYERS;
    private final Calculation calculation;

    public NerdleGame(final CalculationGenerator generator) {
        this.calculation = generator.nextCalculation();
    }

    public void registerPlayer(final Player player) {
        if(gameState != GameState.WAIT_FOR_PLAYERS) {
            throw new GameStateException("Players can only be registered in " + GameState.WAIT_FOR_PLAYERS.name() + " state. Current state is " + gameState.name());
        }
        players.add(player);
        if(players.size() == MAX_PLAYERS) {
            gameState = gameState.nextState();
        }
    }

    public GuessResult makeGuess(final Guess guess) {
        if(gameState != GameState.GUESSING) {
            throw new GameStateException("Guesses can only be made during " + GameState.GUESSING.name() + " state. Current state is " + gameState.name());
        }
        return GuessResult.createFromGuess(calculation, guess.calculation());
    }

    public int currentPlayerCount() {
        return players.size();
    }

}
