package de.dhbw.nerdlegame;

import de.dhbw.nerdlegame.message.Message;
import de.dhbw.nerdlegame.resource.GuessResultResource;

import java.util.Arrays;

public class ConnectionObserverImpl implements ConnectionObserver {

    @Override
    public void receiveMessage(String content) {
        final Message message = new Message(content);
        final StringBuilder stringBuilder = new StringBuilder();
        Arrays.stream(new GuessResultResource(message.getContent()).getDigitResults()).forEach(digit -> stringBuilder.append(digit.guessedDigit() + ": " + digit.resultType() + "; "));
        System.out.println(stringBuilder);
    }

}
