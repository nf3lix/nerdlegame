package de.dhbw.nerdlegame;

import de.dhbw.nerdlegame.player.Player;
import de.dhbw.nerdlegame.player.PlayerId;
import de.dhbw.nerdlegame.player.PlayerName;
import de.dhbw.nerdlegame.server.ClientHandler;
import de.dhbw.nerdlegame.server.Game;

import java.util.*;

public class GameQueue implements ClientConnectedObserver {

    private final Queue<ClientHandler> queue = new LinkedList<>();
    private final Map<ClientHandler, Game> clientsInGame = new HashMap<>();

    @Override
    public void onClientConnected(final ClientHandler clientHandler) {
        queue.add(clientHandler);
        if(queue.size() >= NerdleGame.MAX_PLAYERS) {
            startNewGame();
        }
    }

    private void startNewGame() {
        final Map<ClientHandler, Player> clients = new HashMap<>();
        final NerdleGame nerdleGame = new NerdleGame(new CalculationGeneratorImpl(), new GameTimerImpl());
        for(int socketCount = 0; socketCount < NerdleGame.MAX_PLAYERS; socketCount++) {
            final ClientHandler clientHandler = queue.remove();
            final Player player = new Player(new PlayerId(UUID.randomUUID()), new PlayerName("Player" + (socketCount + 1)));
            nerdleGame.registerPlayer(player);
            clients.put(clientHandler, player);
            clientHandler.addClientMessageObserver(new ClientMessageObserverImpl(clientHandler, player, nerdleGame));
        }
        final Game game = new Game(nerdleGame, clients);
        clients.keySet().forEach(client -> clientsInGame.put(client, game));
    }

}
