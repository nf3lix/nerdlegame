package de.dhbw.nerdlegame;

import de.dhbw.nerdlegame.message.Message;
import de.dhbw.nerdlegame.resource.GuessResultResource;

import java.util.Arrays;

public class ConnectionObserverImpl implements ConnectionObserver {

    @Override
    public void onMessageReceived(String content) {
        final Message message = new Message(content);
        if(message.getPrefix().equals("GAMESTATE")) {
            System.out.println(message.getContent());
            return;
        }
        final StringBuilder stringBuilder = new StringBuilder();
        Arrays.stream(new GuessResultResource(message.getContent()).getDigitResults()).forEach(digit -> stringBuilder.append(digit.guessedDigit() + ": " + digit.resultType() + "; "));
        System.out.println(stringBuilder);
    }

}
