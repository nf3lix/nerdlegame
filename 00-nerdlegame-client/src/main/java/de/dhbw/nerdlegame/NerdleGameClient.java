package de.dhbw.nerdlegame;

import de.dhbw.nerdlegame.ui.MainWindow;

import java.io.IOException;

public class NerdleGameClient {

    public static void main(String[] args) throws IOException {
        new MainWindow();
        final ConnectionObserver observer = new ConnectionObserverImpl();
        final Client client = new Client("127.0.0.1", 5000, observer);
    }

}
