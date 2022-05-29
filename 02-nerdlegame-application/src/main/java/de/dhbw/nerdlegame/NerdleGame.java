package de.dhbw.nerdlegame;

import de.dhbw.nerdlegame.calculation.Calculation;
import de.dhbw.nerdlegame.guess.Guess;
import de.dhbw.nerdlegame.guess.GuessResult;
import de.dhbw.nerdlegame.player.Player;

import java.util.*;

public class NerdleGame implements GameStateObservable, DetermineWinnerObservable {

    private final List<Player> players = new ArrayList<>();

    private final Set<GameStateObserver> gameStateObservers = new HashSet<>();
    private final Set<DetermineWinnerObserver> determineWinnerObservers = new HashSet<>();
    private final Map<Player, Integer> guesses = new HashMap<>();

    public static final int MAX_PLAYERS = 2;
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
        guesses.put(player, 0);
        if(players.size() == MAX_PLAYERS) {
            gameState = gameState.nextState();
            notifyGameStateObservers();
        }
    }

    public GuessResult makeGuess(final Guess guess) {
        if(gameState != GameState.GUESSING) {
            throw new GameStateException("Guesses can only be made during " + GameState.GUESSING.name() + " state. Current state is " + gameState.name());
        }
        guesses.put(guess.player(), guesses.get(guess.player()) + 1);
        final GuessResult result = GuessResult.createFromGuess(calculation, guess.calculation());
        if(result.isCorrect()) {
            System.out.println(true);
            gameState.nextState();
            notifyGameStateObservers();
            notifyWinnerDeterminedObservers(guess.player());
        }
        return GuessResult.createFromGuess(calculation, guess.calculation());
    }

    public int amountOfGuesses(final Player player) {
        return guesses.get(player);
    }

    @Override
    public void addGameStateChangedListener(GameStateObserver observer) {
        this.gameStateObservers.add(observer);
    }

    @Override
    public void notifyGameStateObservers() {
        gameStateObservers.forEach(observer -> observer.onGameStateChanged(gameState));
    }

    @Override
    public void addWinnerDeterminedListener(final DetermineWinnerObserver observer) {
        determineWinnerObservers.add(observer);
    }

    @Override
    public void notifyWinnerDeterminedObservers(final Player player) {
        determineWinnerObservers.forEach(observer -> observer.onWinnerDetermined(player));
    }
}
