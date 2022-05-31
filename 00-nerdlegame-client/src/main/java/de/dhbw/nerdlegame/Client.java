package de.dhbw.nerdlegame;

import de.dhbw.nerdlegame.calculation.Calculation;

import java.io.*;
import java.net.Socket;

public class Client implements CommandObserver {

    private final Socket socket;
    private final PrintWriter out;
    private final ServerConnection serverConnection;

    public Client(final String address, final int port, final ConnectionObserver observer) throws IOException {
        this.socket = new Socket(address, port);
        this.serverConnection = new ServerConnection(socket, observer);
        this.out = new PrintWriter(socket.getOutputStream(), true);
    }

    public void start() {
        new Thread(serverConnection).start();
    }

    @Override
    public void onCommand(String command) {
        System.out.println(command);
        if(command.equals("quit")) {
            try {
                socket.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            System.exit(0);
        }
        if(command.startsWith("guess ")) {
            new Calculation(command.split(" ")[1]);
            out.println(command);
        }
    }

}
