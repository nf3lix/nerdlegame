package de.dhbw.nerdlegame.server;

import de.dhbw.nerdlegame.message.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class ClientHandler implements Runnable, Receiver, ClientMessageReceiver, ClientConnectionClosedObservable {

    private final Socket client;
    private final BufferedReader in;
    private final PrintWriter out;
    private final Set<ClientMessageObserver> clientMessageObservers = new HashSet<>();
    private final Set<ClientConnectionClosedObserver> clientConnectionClosedObservers = new HashSet<>();

    public ClientHandler(final Socket socket) throws IOException {
        this.client = socket;
        this.in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        this.out = new PrintWriter(client.getOutputStream(), true);
    }

    @Override
    public void run() {
        try {
            while(true) {
                final String request = in.readLine();
                notifyClientMessageObservers(request);
            }
        } catch (IOException e) {
            Server.log(e.getMessage());
        } finally {
            notifyClientConnectionClosedObservers();
            out.close();
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void closeConnection() {
        try {
            this.client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendMessage(final Message message) {
        try {
            out.println(message.toString());
        } catch (NullPointerException ignored) { }
    }

    @Override
    public void addClientMessageObserver(final ClientMessageObserver observer) {
        clientMessageObservers.add(observer);
    }

    @Override
    public void notifyClientMessageObservers(final String message) {
        clientMessageObservers.forEach(observer -> observer.onClientMessageReceived(message));
    }

    @Override
    public void addClientConnectionClosedListener(final ClientConnectionClosedObserver observer) {
        clientConnectionClosedObservers.add(observer);
    }

    @Override
    public void notifyClientConnectionClosedObservers() {
        clientConnectionClosedObservers.forEach(ClientConnectionClosedObserver::onConnectionClosed);
    }
}
