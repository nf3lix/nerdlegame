package de.dhbw.nerdlegame.game;

import de.dhbw.nerdlegame.GameStateException;
import de.dhbw.nerdlegame.NerdleGame;
import de.dhbw.nerdlegame.NoMoreGuessesAvailable;
import de.dhbw.nerdlegame.TooLittleTimeSinceLastGuess;
import de.dhbw.nerdlegame.calculation.Calculation;
import de.dhbw.nerdlegame.guess.Guess;
import de.dhbw.nerdlegame.guess.GuessResult;
import de.dhbw.nerdlegame.message.Message;
import de.dhbw.nerdlegame.message.MessageType;
import de.dhbw.nerdlegame.player.Player;
import de.dhbw.nerdlegame.resource.GuessResultResource;
import de.dhbw.nerdlegame.server.ClientHandler;
import de.dhbw.nerdlegame.server.ClientMessageObserver;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class ClientMessageObserverImpl implements ClientMessageObserver, OnPlayerGuessObservable {

    private final ClientHandler clientHandler;
    private final Player player;
    private final NerdleGame nerdleGame;

    private final Set<OnPlayerGuessObserver> playerGuessObservers = new HashSet<>();

    public ClientMessageObserverImpl(final ClientHandler clientHandler, final Player player, final NerdleGame nerdleGame) {
        this.clientHandler = clientHandler;
        this.player = player;
        this.nerdleGame = nerdleGame;
    }

    @Override
    public void onClientMessageReceived(String message) {
        if(message == null) {
            return;
        }
        if(message.startsWith("guess")) {
            try {
                final Guess guess = new Guess(UUID.randomUUID(), player, new Calculation(message.split(" ")[1]));
                try {
                    final GuessResult guessResult = nerdleGame.makeGuess(guess);
                    clientHandler.sendMessage(new Message(MessageType.GUESS_RESULT, new GuessResultResource(guessResult).toString()));
                    playerGuessObservers.forEach(observer -> observer.onPlayerGuess(guess.player(), guessResult));
                } catch (TooLittleTimeSinceLastGuess e) {
                    clientHandler.sendMessage(new Message(MessageType.TOO_LITTLE_TIME_SINCE_LAST_GUESS_ERROR, e.getMessage()));
                } catch (NoMoreGuessesAvailable e) {
                    clientHandler.sendMessage(new Message(MessageType.NO_MORE_GUESSES_AVAILABLE, e.getMessage()));
                }
            } catch (GameStateException e) {
                clientHandler.sendMessage(new Message(MessageType.GUESSING_NOT_STARTED_YET, e.getMessage()));
            }
        }
    }

    @Override
    public void addOnPlayerGuessListener(OnPlayerGuessObserver observer) {
        playerGuessObservers.add(observer);
    }

}
