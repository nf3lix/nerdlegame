package de.dhbw.nerdlegame.game;

import de.dhbw.nerdlegame.CalculationGenerator;
import de.dhbw.nerdlegame.NerdleGame;
import de.dhbw.nerdlegame.calculation.Calculation;
import de.dhbw.nerdlegame.guess.GuessResult;
import de.dhbw.nerdlegame.message.Message;
import de.dhbw.nerdlegame.message.MessageType;
import de.dhbw.nerdlegame.player.Player;
import de.dhbw.nerdlegame.player.PlayerId;
import de.dhbw.nerdlegame.player.PlayerName;
import de.dhbw.nerdlegame.resource.GuessResultResource;
import de.dhbw.nerdlegame.server.ClientConnectedObserver;
import de.dhbw.nerdlegame.server.ClientHandler;

import java.util.*;

public class GameQueue implements ClientConnectedObserver {

    private static final String LOG_PREFIX = "[GAME QUEUE]";

    private final Queue<ClientHandler> queue = new LinkedList<>();
    private final Map<ClientHandler, NerdleGame> clientsInGame = new HashMap<>();
    private final CalculationGenerator calculationGenerator;

    public GameQueue(final CalculationGenerator calculationGenerator) {
        this.calculationGenerator = calculationGenerator;
    }

    @Override
    public void onClientConnected(final ClientHandler clientHandler) {
        log("Player joined game queue");
        queue.add(clientHandler);
        if(queue.size() >= NerdleGame.MAX_PLAYERS) {
            startNewGame();
            return;
        }
        log("Players in queue: " + queue.size() + "; Players in game: " + clientsInGame.size());
    }

    private void startNewGame() {
        log("Start new game...");
        final Map<ClientHandler, Player> clients = new HashMap<>();
        final NerdleGame nerdleGame = new NerdleGame(calculationGenerator, new GameTimerImpl());
        for(int socketCount = 0; socketCount < NerdleGame.MAX_PLAYERS; socketCount++) {
            registerPlayerFromQueue(nerdleGame, clients, "Player" + (socketCount + 1));
        }
        addOnWinListener(nerdleGame, clients);
        addOnLastRemainingPlayerListener(nerdleGame, clients);
        clients.keySet().forEach(client -> clientsInGame.put(client, nerdleGame));
        log("Players in queue: " + queue.size() + "; Players in game: " + clientsInGame.size());
    }

    private void registerPlayerFromQueue(final NerdleGame nerdleGame, final Map<ClientHandler, Player> clients, final String playerName) {
        final ClientHandler clientHandler = queue.remove();
        final Player player = new Player(new PlayerId(UUID.randomUUID()), new PlayerName(playerName));
        nerdleGame.registerPlayer(player);
        clientHandler.sendMessage(new Message(MessageType.GAME_STARTS, "Game starts"));
        clients.put(clientHandler, player);
        clientHandler.addClientMessageObserver(new ClientMessageObserverImpl(clientHandler, player, nerdleGame));
        clientHandler.addClientConnectionClosedListener(() -> {
            clients.keySet().forEach(client -> client.sendMessage(new Message(MessageType.PLAYER_WINS, player.playerName() + " left the game")));
            log("Players in queue: " + queue.size() + "; Players in game: " + clientsInGame.size());
        });
    }

    private void addOnWinListener(final NerdleGame nerdleGame, final Map<ClientHandler, Player> clients) {
        nerdleGame.addOnWinObserver(player -> {
            clients.keySet().forEach(client -> {
                if(clients.get(client) == player) {
                    final GuessResult guessResult = GuessResult.createFromGuess(new Calculation("11+11=22"), new Calculation("11+11=22"));
                    client.sendMessage(new Message(MessageType.GUESS_RESULT, new GuessResultResource(guessResult).toString()));
                    client.sendMessage(new Message(MessageType.PLAYER_WINS, "You guessed the calculation after " + nerdleGame.amountOfGuesses(player) + " guesses. You have won!"));
                } else {
                    client.sendMessage(new Message(MessageType.PLAYER_WINS, player.playerName() + " guessed the calculation after " + nerdleGame.amountOfGuesses(player) + " guesses"));
                }
                clientsInGame.remove(client);
                client.closeConnection();
                log("Players in queue: " + queue.size() + "; Players in game: " + clientsInGame.size());
            });
        });
    }

    private void addOnLastRemainingPlayerListener(final NerdleGame nerdleGame, final Map<ClientHandler, Player> clients) {
        nerdleGame.addOnLastRemainingPlayerListener(player -> clients.keySet().forEach(client -> {
            if(clients.get(client) == player) {
                client.sendMessage(new Message(MessageType.PLAYER_WINS, "You are the last remaining player. You have won!"));
                return;
            }
            client.sendMessage(new Message(MessageType.PLAYER_WINS, player.playerName() + " has won since he is the last remaining player"));
        }));
    }

    private void log(final String logMessage) {
        System.out.println(LOG_PREFIX + " " + logMessage);
    }

}