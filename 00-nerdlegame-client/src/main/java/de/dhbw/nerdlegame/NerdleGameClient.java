package de.dhbw.nerdlegame;

import de.dhbw.nerdlegame.ui.MainWindow;

import java.io.IOException;

public class NerdleGameClient {

    public static void main(String[] args) {
        final MainWindow window = new MainWindow();
        window.addFindGameButtonClickListener(action -> {
            window.resetGame();
            final Runnable runnable = () -> {
                final ConnectionObserver observer = new ConnectionObserverImpl(window);
                try {
                    final Client client = new Client("127.0.0.1", 5000, observer);
                    window.addCommandListener(client);
                    client.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            };
            new Thread(runnable).start();
        });
    }

}
