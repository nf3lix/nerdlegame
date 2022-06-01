package de.dhbw.nerdlegame.response_action;

import de.dhbw.nerdlegame.message.Message;
import de.dhbw.nerdlegame.ui.MainWindow;

public class DisplayOpponentGuess implements OnResponseAction {

    private final MainWindow mainWindow;

    public DisplayOpponentGuess(final MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    @Override
    public void run(Message message) {
        mainWindow.addLogMessage(message.getContent());
    }

}
