package de.dhbw.nerdlegame.response_action;

import de.dhbw.nerdlegame.message.Message;
import de.dhbw.nerdlegame.ui.MainWindow;

public class DisplayGameStarts implements OnResponseAction {

    private final MainWindow mainWindow;

    public DisplayGameStarts(final MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    @Override
    public void run(Message message) {
        mainWindow.enableRow(0);
        System.out.println(message.getContent());
    }

}
