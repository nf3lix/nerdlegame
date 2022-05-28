package de.dhbw.nerdlegame;

public class NerdleGameServer {

    public static void main(String[] args) {
        final NerdleGame nerdleGame = new NerdleGame(new CalculationGeneratorImpl());
        nerdleGame.addGameStateChangedListener(gameState -> System.out.println("GameState changed: " + gameState.name()));
        final ConcreteSocketObserver socketObserver = new ConcreteSocketObserver(nerdleGame);
        final Server server = new Server(5000, socketObserver);
    }

}
