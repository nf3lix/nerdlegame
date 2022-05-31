package de.dhbw.nerdlegame.response_action;

import de.dhbw.nerdlegame.message.Message;
import de.dhbw.nerdlegame.ui.MainWindow;

public class DisplayGameStateChanged implements OnResponseAction {

    private final MainWindow mainWindow;

    public DisplayGameStateChanged(final MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    @Override
    public void run(Message message) {
        this.mainWindow.addLogMessage(message.getContent());
        System.out.println(message.getContent());
    }

}
