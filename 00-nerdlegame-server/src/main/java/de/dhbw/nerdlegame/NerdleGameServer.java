package de.dhbw.nerdlegame;

import de.dhbw.nerdlegame.server.Server;

import java.io.IOException;

public class NerdleGameServer {

    private static final String PREFIX = "[SERVER]";

    public static void main(String[] args) throws IOException {
        final NerdleGame nerdleGame = new NerdleGame(new CalculationGeneratorImpl());
        nerdleGame.addGameStateChangedListener(gameState -> System.out.println("GameState changed: " + gameState.name()));
        final SocketConnectionObserverImpl socketObserver = new SocketConnectionObserverImpl(nerdleGame);
        final ClientHandlerObserver clientObserver = new ClientHandlerObserverImpl(nerdleGame);
        final Server server = new Server(5000, socketObserver, clientObserver);
    }

    public static void logMessage(final String message) {
        System.out.println(PREFIX + " " + message);
    }

}
