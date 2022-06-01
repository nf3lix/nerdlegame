package de.dhbw.nerdlegame.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server implements ClientConnectedObservable {

    public final static String LOG_PREFIX = "[SERVER]";

    private final ExecutorService pool = Executors.newCachedThreadPool();
    private final ServerSocket server;
    private final Set<ClientConnectedObserver> clientConnectedObservers = new HashSet<>();

    public Server(final int port) throws IOException {
        this.server = new ServerSocket(port);
    }

    public void start() throws IOException {
        while (true) {
            registerPlayer();
        }
    }

    private void registerPlayer() throws IOException {
        final Socket client = server.accept();
        final ClientHandler clientHandler = new ClientHandler(client);
        log("Client connected");
        notifyClientConnectedListeners(clientHandler);
        pool.execute(clientHandler);
    }

    @Override
    public void addClientConnectedListener(final ClientConnectedObserver observer) {
        this.clientConnectedObservers.add(observer);
    }

    @Override
    public void notifyClientConnectedListeners(final ClientHandler clientHandler) {
        clientConnectedObservers.forEach(observer -> observer.onClientConnected(clientHandler));
    }

    public static void log(final String logMessage) {
        System.out.println(LOG_PREFIX + " " + logMessage);
    }

}
