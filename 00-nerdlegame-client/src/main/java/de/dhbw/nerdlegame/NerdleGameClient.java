package de.dhbw.nerdlegame;

import java.io.IOException;

public class NerdleGameClient {

    public static void main(String[] args) throws IOException {
        final ConnectionObserver observer = new ConnectionObserverImpl();
        final Client client = new Client("127.0.0.1", 5000, observer);
    }

}
