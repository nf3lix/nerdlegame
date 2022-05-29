package de.dhbw.nerdlegame.server;

import de.dhbw.nerdlegame.Receiver;
import de.dhbw.nerdlegame.ServerConnectionObserver;
import de.dhbw.nerdlegame.message.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class ClientHandler implements Runnable, Receiver, ClientMessageReceiver {

    private final Socket client;
    private final BufferedReader in;
    private final PrintWriter out;
    private final ServerConnectionObserver observer;
    private final Set<ClientMessageObserver> clientMessageObservers = new HashSet<>();

    public ClientHandler(final Socket socket, final ServerConnectionObserver observer) throws IOException {
        this.observer = observer;
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
            System.err.println(e.getMessage());
        } finally {
            out.close();
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void sendMessage(final Message message) {
        out.println(message.toString());
    }

    @Override
    public void addClientMessageObserver(final ClientMessageObserver observer) {
        clientMessageObservers.add(observer);
    }

    @Override
    public void notifyClientMessageObservers(final String message) {
        clientMessageObservers.forEach(observer -> observer.onClientMessageReceived(message));
    }
}
