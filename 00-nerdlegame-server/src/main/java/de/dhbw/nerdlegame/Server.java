package de.dhbw.nerdlegame;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private Socket socket = null;
    private ServerSocket server = null;
    private DataInputStream inputStream = null;

    public Server(final int port) {
        try {
            server = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true) {
            try {
                socket = server.accept();
                System.out.println("client connected");
            } catch (IOException e) {
                e.printStackTrace();
            }
            new EchoThread(socket).start();
        }
    }

}
