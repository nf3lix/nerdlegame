package de.dhbw.nerdlegame;

import de.dhbw.nerdlegame.calculation.Calculation;

import java.io.*;
import java.net.Socket;

public class Client {

    public Client(final String address, final int port, final ConnectionObserver observer) throws IOException {
        final Socket socket = new Socket(address, port);
        final ServerConnection serverConnection = new ServerConnection(socket, observer);
        final BufferedReader commandLine = new BufferedReader(new InputStreamReader(System.in));
        final PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        new Thread(serverConnection).start();
        while (true) {
            String command = commandLine.readLine();
            if(command.equals("quit")) break;
            if(command.startsWith("guess ")) {
                new Calculation(command.split(" ")[1]);
                out.println(command);
            }
        }
        socket.close();
        System.exit(0);
    }

}
