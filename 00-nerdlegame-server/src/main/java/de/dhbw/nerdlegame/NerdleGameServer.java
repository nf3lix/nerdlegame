package de.dhbw.nerdlegame;

import de.dhbw.nerdlegame.server.Server;

import java.io.IOException;

public class NerdleGameServer {

    private static final String PREFIX = "[SERVER]";

    public static void main(String[] args) throws IOException {
        final NerdleGame nerdleGame = new NerdleGame(new CalculationGeneratorImpl(), new GameTimerImpl());
        nerdleGame.addGameStateChangedListener(gameState -> logMessage("GameState changed: " + gameState.name()));
        final Server server = new Server(5000);
        server.addClientConnectedListener(new GameQueue());
        server.start();
    }

    public static void logMessage(final String message) {
        System.out.println(PREFIX + " " + message);
    }

}
