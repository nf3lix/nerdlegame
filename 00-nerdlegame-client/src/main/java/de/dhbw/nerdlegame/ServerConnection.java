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

    public ServerConnection(final Socket server) throws IOException {
        this.server = server;
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
                System.out.println(serverResponse);
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
