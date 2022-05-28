package de.dhbw.nerdlegame.guess;

import de.dhbw.nerdlegame.calculation.Calculation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static de.dhbw.nerdlegame.guess.GuessResultMatcher.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class GuessResultTest {

    @Test
    public void getCorrectResultForDigits() {
        final Calculation calculation = new Calculation("21+14=35");
        final Calculation guess =       new Calculation("22+34=56");
        final GuessResult guessResult = GuessResult.createFromGuess(calculation, guess);
        assertThat(guessResult, hasCorrectDigitsAt(0, 2, 4, 5));
    }

    @Test
    public void getWrongSpottedResultsForDigits() {
        final Calculation calculation = new Calculation("11+22=33");
        final Calculation guess =       new Calculation("22+11=33");
        final GuessResult guessResult = GuessResult.createFromGuess(calculation, guess);
        assertThat(guessResult, hasWrongSpottedDigitsAt(0, 1, 3, 4));
    }

    @Test
    public void getNotContainedResultsForDigits() {
        final Calculation calculation = new Calculation("86-32=54");
        final Calculation guess =       new Calculation("22+11=33");
        final GuessResult guessResult = GuessResult.createFromGuess(calculation, guess);
        assertThat(guessResult, hasNotContainedDigitsAt(1, 2, 3, 4, 7));
    }

    @Test
    public void doNotReturnWrongSpotWhenDigitIsCorrect() {
        final Calculation calculation = new Calculation("11+11=22");
        final Calculation guess =       new Calculation("11+11=22");
        final boolean wrongSpot = DigitResultType.WRONG_SPOT.applies(calculation, guess, 0);
        Assertions.assertFalse(wrongSpot);
    }

}
