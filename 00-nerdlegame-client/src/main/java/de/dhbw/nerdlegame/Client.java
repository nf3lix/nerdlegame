package de.dhbw.nerdlegame;

import java.io.*;
import java.net.Socket;

public class Client {

    public Client(final String address, final int port) throws IOException {
        final Socket socket = new Socket(address, port);
        final ServerConnection serverConnection = new ServerConnection(socket);
        final BufferedReader commandLine = new BufferedReader(new InputStreamReader(System.in));
        final PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        new Thread(serverConnection).start();
        while (true) {
            String command = commandLine.readLine();
            if(command.equals("quit")) break;
            out.println(command);
        }
        socket.close();
        System.exit(0);
    }

}
