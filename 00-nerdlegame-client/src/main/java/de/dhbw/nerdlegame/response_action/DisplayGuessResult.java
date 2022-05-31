package de.dhbw.nerdlegame.response_action;

import de.dhbw.nerdlegame.guess.GuessResult;
import de.dhbw.nerdlegame.mapper.GuessResultMapper;
import de.dhbw.nerdlegame.message.Message;
import de.dhbw.nerdlegame.resource.GuessResultResource;
import de.dhbw.nerdlegame.ui.MainWindow;

public class DisplayGuessResult implements OnResponseAction {

    private final MainWindow mainWindow;
    private int resultCount = 0;

    public DisplayGuessResult(final MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    @Override
    public void run(final Message message) {
        final GuessResult guessResult = new GuessResultMapper().toDomainModel(new GuessResultResource(message.getContent()));
        mainWindow.disableRow(resultCount);
        mainWindow.enableRow(resultCount + 1);
        mainWindow.displayGuessResult(resultCount, guessResult);
        resultCount++;
    }

}
