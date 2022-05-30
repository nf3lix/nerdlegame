package de.dhbw.nerdlegame;

import de.dhbw.nerdlegame.calculation.Calculation;
import de.dhbw.nerdlegame.guess.Guess;
import de.dhbw.nerdlegame.guess.GuessResult;
import de.dhbw.nerdlegame.player.Player;

import java.util.*;

public class NerdleGame implements GameStateObservable, OnWinObservable {

    private final List<Player> players = new ArrayList<>();

    private final Set<GameStateObserver> gameStateObservers = new HashSet<>();
    private final Set<OnWinObserver> winObservers = new HashSet<>();
    private final Map<Player, Integer> amountOfGuesses = new HashMap<>();
    private final Map<Player, Long> lastGuesses = new HashMap<>();

    public static final int MAX_PLAYERS = 2;
    public static final int MIN_TIME_BETWEEN_GUESSES = 5 * 1000;

    private final GameTimer gameTimer;
    private GameState gameState = GameState.WAIT_FOR_PLAYERS;
    private final Calculation calculation;

    public NerdleGame(final CalculationGenerator generator, final GameTimer gameTimer) {
        this.calculation = generator.nextCalculation();
        this.gameTimer = gameTimer;
        for(int i = 0; i < 8; i++) {
            System.out.println(calculation.getDigit(i));
        }
    }

    public void registerPlayer(final Player player) {
        if(gameState != GameState.WAIT_FOR_PLAYERS) {
            throw new GameStateException("Players can only be registered in " + GameState.WAIT_FOR_PLAYERS.name() + " state. Current state is " + gameState.name());
        }
        players.add(player);
        amountOfGuesses.put(player, 0);
        lastGuesses.put(player, 0L);
        if(players.size() == MAX_PLAYERS) {
            gameState = gameState.nextState();
            notifyGameStateObservers();
        }
    }

    public GuessResult makeGuess(final Guess guess) {
        if(gameState != GameState.GUESSING) {
            throw new GameStateException("Guesses can only be made during " + GameState.GUESSING.name() + " state. Current state is " + gameState.name());
        }
        if(gameTimer.currentTimeInMillis() - lastGuesses.get(guess.player()) < MIN_TIME_BETWEEN_GUESSES) {
            throw new TooLittleTimeSinceLastGuess("You have to wait at least " + (MIN_TIME_BETWEEN_GUESSES / 1000) + "seconds until next guess");
        }
        lastGuesses.put(guess.player(), gameTimer.currentTimeInMillis());
        amountOfGuesses.put(guess.player(), amountOfGuesses.get(guess.player()) + 1);
        final GuessResult result = GuessResult.createFromGuess(calculation, guess.calculation());
        if(result.isCorrect()) {
            gameState.nextState();
            notifyGameStateObservers();
            notifyOnWinObservers(guess.player());
        }
        return GuessResult.createFromGuess(calculation, guess.calculation());
    }

    public int amountOfGuesses(final Player player) {
        return amountOfGuesses.get(player);
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
    public void addOnWinObserver(final OnWinObserver observer) {
        winObservers.add(observer);
    }

    @Override
    public void notifyOnWinObservers(final Player player) {
        winObservers.forEach(observer -> observer.onWinnerDetermined(player));
    }
}
