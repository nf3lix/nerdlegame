package de.dhbw.nerdlegame.response_action;

import de.dhbw.nerdlegame.message.Message;
import de.dhbw.nerdlegame.ui.MainWindow;

import javax.swing.*;

public class DisplayPlayerWins implements OnResponseAction {

    private final MainWindow mainWindow;

    public DisplayPlayerWins(final MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    @Override
    public void run(Message message) {
        this.mainWindow.disableAllRows();
        this.mainWindow.addLogMessage(message.getContent());
        this.mainWindow.addLogMessage("Game over");
        JOptionPane.showMessageDialog(mainWindow.frame(), message.getContent());
    }

}
