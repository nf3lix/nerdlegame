package de.dhbw.nerdlegame;

import de.dhbw.nerdlegame.resource.GuessResultResource;

import java.util.Arrays;

public class ConnectionObserverImpl implements ConnectionObserver {

    @Override
    public void receiveMessage(String message) {
        final StringBuilder stringBuilder = new StringBuilder();
        Arrays.stream(new GuessResultResource(message).getDigitResults()).forEach(digit -> stringBuilder.append(digit.guessedDigit() + ": " + digit.resultType() + "; "));
        System.out.println(stringBuilder);
    }

}
