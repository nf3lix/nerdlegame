package de.dhbw.nerdlegame.game;

import de.dhbw.nerdlegame.NerdleGame;
import de.dhbw.nerdlegame.calculation.Calculation;
import de.dhbw.nerdlegame.guess.GuessResult;
import de.dhbw.nerdlegame.listeners.OnWinObserver;
import de.dhbw.nerdlegame.message.Message;
import de.dhbw.nerdlegame.message.MessageType;
import de.dhbw.nerdlegame.player.Player;
import de.dhbw.nerdlegame.resource.GuessResultResource;
import de.dhbw.nerdlegame.server.ClientHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class OnWinListenerImpl implements OnWinObserver {

    private final Queue<ClientHandler> queue;
    private final NerdleGame nerdleGame;
    private final Map<ClientHandler, NerdleGame> clientsInGame;
    private final Map<ClientHandler, Player> clients;

    public OnWinListenerImpl(final Queue<ClientHandler> queue, final NerdleGame nerdleGame, final Map<ClientHandler, NerdleGame> clientsInGame, final Map<ClientHandler, Player> clients) {
        this.queue = queue;
        this.nerdleGame = nerdleGame;
        this.clientsInGame = clientsInGame;
        this.clients = clients;
    }

    @Override
    public void onWinnerDetermined(Player player) {
        clients.keySet().iterator().forEachRemaining(client -> {
            if(clients.get(client) == player) {
                final GuessResult guessResult = GuessResult.createFromGuess(new Calculation("11+11=22"), new Calculation("11+11=22"));
                client.sendMessage(new Message(MessageType.GUESS_RESULT, new GuessResultResource(guessResult).toString()));
                client.sendMessage(new Message(MessageType.PLAYER_WINS, "You guessed the calculation after " + nerdleGame.amountOfGuesses(player) + " guesses. You have won!"));
            } else {
                client.sendMessage(new Message(MessageType.PLAYER_WINS, player.playerName() + " guessed the calculation after " + nerdleGame.amountOfGuesses(player) + " guesses"));
            }
            clientsInGame.remove(client);
            client.closeConnection();
            GameQueue.log("Players in queue: " + queue.size() + "; Players in game: " + clientsInGame.size());
        });
        // clients.keySet().forEach(client -> {
        //
        // });
    }

}
