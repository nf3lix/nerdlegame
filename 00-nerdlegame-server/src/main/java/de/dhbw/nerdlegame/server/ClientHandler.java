package de.dhbw.nerdlegame.server;

import de.dhbw.nerdlegame.ClientHandlerObserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable {

    private final Socket client;
    private final BufferedReader in;
    private final PrintWriter out;
    private ArrayList<ClientHandler> clients;
    private final ClientHandlerObserver observer;

    public ClientHandler(final Socket socket, final ArrayList<ClientHandler> clients, final ClientHandlerObserver observer) throws IOException {
        this.observer = observer;
        this.client = socket;
        this.clients = clients;
        this.in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        this.out = new PrintWriter(client.getOutputStream(), true);
    }

    @Override
    public void run() {
        try {
            while(true) {
                final String request = in.readLine();
                observer.guess(request);
                broadcast("Player made a guess");
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

    private void broadcast(final String message) {
        clients.forEach(client -> client.out.println(message));
    }

}
