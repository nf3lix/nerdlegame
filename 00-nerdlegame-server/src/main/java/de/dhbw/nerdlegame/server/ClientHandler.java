package de.dhbw.nerdlegame.server;

import de.dhbw.nerdlegame.GameStateException;
import de.dhbw.nerdlegame.ServerConnectionObserver;
import de.dhbw.nerdlegame.calculation.Calculation;
import de.dhbw.nerdlegame.guess.Guess;
import de.dhbw.nerdlegame.player.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.UUID;

public class ClientHandler implements Runnable {

    private final Player player;
    private final Socket client;
    private final BufferedReader in;
    private final PrintWriter out;
    private ArrayList<ClientHandler> clients;
    private final ServerConnectionObserver observer;

    public ClientHandler(final Player player, final Socket socket, final ArrayList<ClientHandler> clients, final ServerConnectionObserver observer) throws IOException {
        this.player = player;
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
                System.out.println(request);
                if(request.startsWith("guess")) {
                    try {
                        observer.onGuess(new Guess(UUID.randomUUID(), player, new Calculation(request.split(" ")[1])));
                    } catch (GameStateException e) {
                        this.out.println("Guessing has not started yet");
                    }
                }
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
