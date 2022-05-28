package de.dhbw.nerdlegame.server;

import de.dhbw.nerdlegame.ClientHandlerObserver;
import de.dhbw.nerdlegame.NerdleGame;
import de.dhbw.nerdlegame.ServerConnectionObserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private final ArrayList<ClientHandler> clients = new ArrayList<>();
    private final ExecutorService pool = Executors.newCachedThreadPool();
    private final ServerSocket server;
    private final ServerConnectionObserver socketObserver;

    public Server(final int port, final ServerConnectionObserver serverObserver, final ClientHandlerObserver clientObserver) throws IOException {
        this.socketObserver = serverObserver;
        this.server = new ServerSocket(port);
        int connectedPlayerCont = 0;
        while (connectedPlayerCont <= NerdleGame.MAX_PLAYERS) {
            final Socket client = server.accept();
            connectedPlayerCont++;
            final ClientHandler clientHandler = new ClientHandler(client, clients, clientObserver);
            clients.add(clientHandler);
            pool.execute(clientHandler);
            socketObserver.onClientConnected();
        }
    }

}
