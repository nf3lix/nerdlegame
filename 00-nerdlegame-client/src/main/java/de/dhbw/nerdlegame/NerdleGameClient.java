package de.dhbw.nerdlegame;

import de.dhbw.nerdlegame.ui.MainWindow;

import javax.swing.*;
import java.io.IOException;

public class NerdleGameClient {

    public static void main(String[] args) {
        final String serverAddress = args[0];
        final int serverPort = Integer.parseInt(args[1]);
        final MainWindow window = new MainWindow();
        window.addFindGameButtonClickListener(action -> {
            window.resetGame();
            final Runnable runnable = () -> {
                window.setFindGameButtonEnabled(false);
                final ConnectionObserver observer = new ConnectionObserverImpl(window);
                try {
                    final Client client = new Client(serverAddress, serverPort, observer);
                    window.addCommandListener(client);
                    client.start();
                } catch (IOException e) {
                    window.setFindGameButtonEnabled(true);
                    JOptionPane.showMessageDialog(window.frame(), e.getMessage() + ". Make sure the server is running.");
                    e.printStackTrace();
                }
            };
            new Thread(runnable).start();
        });
    }

}
