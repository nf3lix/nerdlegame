package de.dhbw.nerdlegame.response_action;

import de.dhbw.nerdlegame.guess.GuessResult;
import de.dhbw.nerdlegame.mapper.GuessResultMapper;
import de.dhbw.nerdlegame.message.Message;
import de.dhbw.nerdlegame.resource.GuessResultResource;
import de.dhbw.nerdlegame.ui.MainWindow;

public class DisplayGuessResult implements OnResponseAction {

    private final MainWindow mainWindow;

    public DisplayGuessResult(final MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    @Override
    public void run(final Message message) {
        final StringBuilder stringBuilder = new StringBuilder();
        final GuessResult guessResult = new GuessResultMapper().toDomainModel(new GuessResultResource(message.getContent()));
        mainWindow.displayGuessResult(0, guessResult);
        // Arrays.stream(new GuessResultResource(message.getContent()).getDigitResults()).forEach(digit -> stringBuilder.append(digit.guessedDigit() + ": " + digit.resultType() + "; "));
        // System.out.println(stringBuilder);
    }

}
