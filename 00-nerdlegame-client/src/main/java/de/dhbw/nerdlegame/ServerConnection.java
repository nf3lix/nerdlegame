package de.dhbw.nerdlegame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerConnection implements Runnable {

    private final Socket server;
    private final BufferedReader in;
    private final PrintWriter out;
    private final ConnectionObserver observer;

    public ServerConnection(final Socket server, final ConnectionObserver observer) throws IOException {
        this.server = server;
        this.observer = observer;
        this.in = new BufferedReader(new InputStreamReader(server.getInputStream()));
        this.out = new PrintWriter(server.getOutputStream(), true);
    }

    @Override
    public void run() {
        String serverResponse;
        try {
            while (true) {
                serverResponse = in.readLine();
                if(serverResponse == null) break;
                observer.onMessageReceived(serverResponse);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
