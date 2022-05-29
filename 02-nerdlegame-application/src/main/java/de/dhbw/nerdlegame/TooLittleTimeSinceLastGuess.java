package de.dhbw.nerdlegame;

public class TooLittleTimeSinceLastGuess extends RuntimeException {

    public TooLittleTimeSinceLastGuess(final String message) {
        super(message);
    }

}
