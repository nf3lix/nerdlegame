package de.dhbw.nerdlegame.response_action;

import de.dhbw.nerdlegame.message.Message;
import de.dhbw.nerdlegame.resource.GuessResultResource;

import java.util.Arrays;

public class DisplayGuessResult implements OnResponseAction {

    @Override
    public void run(final Message message) {
        final StringBuilder stringBuilder = new StringBuilder();
        Arrays.stream(new GuessResultResource(message.getContent()).getDigitResults()).forEach(digit -> stringBuilder.append(digit.guessedDigit() + ": " + digit.resultType() + "; "));
        System.out.println(stringBuilder);
    }

}
