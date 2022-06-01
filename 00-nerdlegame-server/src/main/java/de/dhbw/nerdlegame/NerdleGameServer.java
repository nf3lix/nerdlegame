package de.dhbw.nerdlegame;

import de.dhbw.nerdlegame.server.Server;

import java.io.IOException;

public class NerdleGameServer {

    public static void main(String[] args) throws IOException {
        final Server server = new Server(5000);
        server.addClientConnectedListener(new GameQueue(new CalculationGeneratorImpl()));
        server.start();
    }

}
