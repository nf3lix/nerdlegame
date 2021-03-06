package de.dhbw.nerdlegame.game;

import de.dhbw.nerdlegame.CalculationGenerator;
import de.dhbw.nerdlegame.NerdleGame;
import de.dhbw.nerdlegame.guess.DigitResultType;
import de.dhbw.nerdlegame.guess.GuessResult;
import de.dhbw.nerdlegame.message.Message;
import de.dhbw.nerdlegame.message.MessageType;
import de.dhbw.nerdlegame.player.Player;
import de.dhbw.nerdlegame.player.PlayerId;
import de.dhbw.nerdlegame.player.PlayerName;
import de.dhbw.nerdlegame.server.ClientConnectedObserver;
import de.dhbw.nerdlegame.server.ClientHandler;

import java.util.*;
import java.util.stream.Collectors;

public class GameQueue implements ClientConnectedObserver, OnPlayerGuessObserver {

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
        nerdleGame.addOnLastRemainingPlayerListener(new OnLastRemainingPlayerListenerImpl(clients));
        clients.keySet().forEach(client -> clientsInGame.put(client, nerdleGame));
        log("Players in queue: " + queue.size() + "; Players in game: " + clientsInGame.size());
    }

    private void registerPlayerFromQueue(final NerdleGame nerdleGame, final Map<ClientHandler, Player> clients, final String playerName) {
        final ClientHandler clientHandler = queue.remove();
        final Player player = new Player(new PlayerId(UUID.randomUUID()), new PlayerName(playerName));
        nerdleGame.registerPlayer(player);
        clientHandler.sendMessage(new Message(MessageType.GAME_STARTS, "Game starts"));
        clients.put(clientHandler, player);
        final ClientMessageObserverImpl clientMessageObserver = new ClientMessageObserverImpl(clientHandler, player, nerdleGame);
        clientMessageObserver.addOnPlayerGuessListener((player1, guess) -> clients.keySet().iterator().forEachRemaining(client -> {
            if(clients.get(client) != player1) {
                final int correctDigits = (int) Arrays.stream(guess.getDigitResults()).filter(digit -> digit.resultType() == DigitResultType.CORRECT).count();
                client.sendMessage(new Message(MessageType.PLAYER_GUESS, player1.playerName() + " made a guess with " + correctDigits + " correct digits"));
            }
        }));
        clientHandler.addClientMessageObserver(clientMessageObserver);
        clientHandler.addClientConnectionClosedListener(() -> {
            clients.remove(clientHandler);
            if(clients.size() == 1) {
                clients.keySet().iterator().next().sendMessage(new Message(MessageType.PLAYER_WINS, "You are the last remaining player. You have won!"));
            }
            log("Players in queue: " + queue.size() + "; Players in game: " + clientsInGame.size());
        });
    }

    private void addOnWinListener(final NerdleGame nerdleGame, final Map<ClientHandler, Player> clients) {
        nerdleGame.addOnWinObserver(new OnWinListenerImpl(queue, nerdleGame, clientsInGame, clients));
    }

    static void log(final String logMessage) {
        System.out.println(LOG_PREFIX + " " + logMessage);
    }

    @Override
    public void onPlayerGuess(Player player, GuessResult guess) {

    }

}
