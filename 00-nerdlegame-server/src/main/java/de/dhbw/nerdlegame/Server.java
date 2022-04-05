package de.dhbw.nerdlegame;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private Socket socket = null;
    private ServerSocket server = null;
    private DataInputStream inputStream = null;

    private SocketObserver socketObserver;

    public Server(final int port, final SocketObserver observer) {
        this.socketObserver = observer;
        try {
            server = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int connectedPlayersCount = 0;
        while (connectedPlayersCount <= NerdleGame.MAX_PLAYERS) {
            try {
                socket = server.accept();
                System.out.println("client connected");
            } catch (IOException e) {
                e.printStackTrace();
            }
            new EchoThread(socket).start();
            socketObserver.onClientConnected();
            connectedPlayersCount++;
        }
    }

}
