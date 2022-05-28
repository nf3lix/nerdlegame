package de.dhbw.nerdlegame.server;

import de.dhbw.nerdlegame.NerdleGame;
import de.dhbw.nerdlegame.ServerConnectionObserver;
import de.dhbw.nerdlegame.message.Message;
import de.dhbw.nerdlegame.player.Player;
import de.dhbw.nerdlegame.player.PlayerId;
import de.dhbw.nerdlegame.player.PlayerName;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private final ArrayList<ClientHandler> clients = new ArrayList<>();
    private final ExecutorService pool = Executors.newCachedThreadPool();
    private final ServerSocket server;
    private final ServerConnectionObserver socketObserver;

    public Server(final int port, final ServerConnectionObserver serverObserver, final NerdleGame nerdleGame) throws IOException {
        nerdleGame.addWinnerDeterminedListener(player -> broadCastMessage(new Message("GAMESTATE", player.playerName() + " win")));
        this.socketObserver = serverObserver;
        this.server = new ServerSocket(port);
        int connectedPlayerCont = 0;
        while (connectedPlayerCont <= NerdleGame.MAX_PLAYERS) {
            connectedPlayerCont++;
            registerPlayer("Player" + connectedPlayerCont);
        }
    }

    private void registerPlayer(final String playerName) throws IOException {
        final Socket client = server.accept();
        final Player player = new Player(new PlayerId(UUID.randomUUID()), new PlayerName(playerName));
        final ClientHandler clientHandler = new ClientHandler(player, client, clients, socketObserver);
        clients.add(clientHandler);
        pool.execute(clientHandler);
        socketObserver.onPlayerJoined(player);
    }

    public void broadCastMessage(final Message message) {
        clients.forEach(client -> client.sendMessage(message));
    }

}
