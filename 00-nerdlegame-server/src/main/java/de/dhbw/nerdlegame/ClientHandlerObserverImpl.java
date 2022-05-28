package de.dhbw.nerdlegame;

public class ClientHandlerObserverImpl implements ClientHandlerObserver {

    private NerdleGame nerdleGame;

    public ClientHandlerObserverImpl(final NerdleGame nerdleGame) {
        this.nerdleGame = nerdleGame;
    }

    @Override
    public void guess(final String calculation) {
        NerdleGameServer.logMessage("Player made a guess");
    }

}
