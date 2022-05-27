package de.dhbw.nerdlegame;

public class NerdleGameServer {

    public static void main(String[] args) {
        final NerdleGame nerdleGame = new NerdleGame(new CalculationGeneratorImpl());
        final ConcreteSocketObserver socketObserver = new ConcreteSocketObserver(nerdleGame);
        final Server server = new Server(5000, socketObserver);
    }

}
