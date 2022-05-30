package de.dhbw.nerdlegame;

import de.dhbw.nerdlegame.ui.MainWindow;

import java.io.IOException;

public class NerdleGameClient {

    public static void main(String[] args) throws IOException {
        final MainWindow window = new MainWindow();
        window.addFindGameButtonClickListener(action -> {
            final Runnable runnable = () -> {
                final ConnectionObserver observer = new ConnectionObserverImpl();
                try {
                    final Client client = new Client("127.0.0.1", 5000, observer);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            };
            new Thread(runnable).start();
        });
    }

}
