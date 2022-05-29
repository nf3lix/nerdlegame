package de.dhbw.nerdlegame;

import de.dhbw.nerdlegame.calculation.Calculation;
import de.dhbw.nerdlegame.guess.Guess;
import de.dhbw.nerdlegame.guess.GuessResult;
import de.dhbw.nerdlegame.message.Message;
import de.dhbw.nerdlegame.message.MessageType;
import de.dhbw.nerdlegame.player.Player;
import de.dhbw.nerdlegame.player.PlayerId;
import de.dhbw.nerdlegame.player.PlayerName;
import de.dhbw.nerdlegame.resource.GuessResultResource;
import de.dhbw.nerdlegame.server.ClientHandler;
import de.dhbw.nerdlegame.server.Game;

import java.util.*;

public class GameQueue implements ClientConnectedObserver {

    private final Queue<ClientHandler> queue = new LinkedList<>();
    private final Map<ClientHandler, Game> clientsInGame = new HashMap<>();

    @Override
    public void onClientConnected(final ClientHandler clientHandler, final ServerConnectionObserver observer) {
        System.out.println(true);
        queue.add(clientHandler);
        if(queue.size() >= NerdleGame.MAX_PLAYERS) {
            startNewGame();
        }
    }

    private void startNewGame() {
        final Map<ClientHandler, Player> clients = new HashMap<>();
        final NerdleGame nerdleGame = new NerdleGame(new CalculationGeneratorImpl(), new GameTimerImpl());
        for(int socketCount = 0; socketCount < NerdleGame.MAX_PLAYERS; socketCount++) {
            System.out.println(socketCount);
            final ClientHandler clientHandler = queue.remove();
            final Player player = new Player(new PlayerId(UUID.randomUUID()), new PlayerName("Player" + (socketCount + 1)));
            nerdleGame.registerPlayer(player);
            clients.put(clientHandler, player);
            clientHandler.addClientMessageObserver(message -> {
                if(message.startsWith("guess")) {
                    try {
                        final Guess guess = new Guess(UUID.randomUUID(), player, new Calculation(message.split(" ")[1]));
                        try {
                            final GuessResult guessResult = nerdleGame.makeGuess(guess);
                            clientHandler.sendMessage(new Message(MessageType.GUESS_RESULT, new GuessResultResource(guessResult).toString()));
                        } catch (TooLittleTimeSinceLastGuess e) {
                            clientHandler.sendMessage(new Message(MessageType.TOO_LITTLE_TIME_SINCE_LAST_GUESS_ERROR, e.getMessage()));
                        }
                    } catch (GameStateException e) { }
                }
            });
        }
        final Game game = new Game(nerdleGame, clients);
        clients.keySet().forEach(client -> clientsInGame.put(client, game));
    }

}
