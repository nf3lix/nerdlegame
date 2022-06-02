package de.dhbw.nerdlegame;

import de.dhbw.nerdlegame.game.CalculationGeneratorImpl;
import de.dhbw.nerdlegame.game.GameQueue;
import de.dhbw.nerdlegame.server.Server;

import java.io.IOException;

public class NerdleGameServer {

    public static void main(String[] args) throws IOException {
        final Server server = new Server(Integer.parseInt(args[0]));
        server.addClientConnectedListener(new GameQueue(new CalculationGeneratorImpl()));
        server.start();
    }

}
