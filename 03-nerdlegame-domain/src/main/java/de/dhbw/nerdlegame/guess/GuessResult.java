package de.dhbw.nerdlegame.guess;

import de.dhbw.nerdlegame.calculation.Calculation;
import de.dhbw.nerdlegame.calculation.CalculationDigit;

public class GuessResult {

    private final GuessDigitResult[] digitResults;

    private GuessResult(final GuessDigitResult[] digitResults) {
        this.digitResults = digitResults;
    }

    public static GuessResult createFromGuess(final Calculation actual, final Calculation guess) {
        final GuessDigitResult[] digitResults = new GuessDigitResult[Calculation.NUMBER_OF_DIGITS];
        for(int digitIndex = 0; digitIndex < Calculation.NUMBER_OF_DIGITS; digitIndex++) {
            final DigitResultType digitResult = DigitResultType.getDigitResult(actual, guess, digitIndex);
            final CalculationDigit guessedDigit = guess.getDigit(digitIndex);
            digitResults[digitIndex] = new GuessDigitResult(guessedDigit, digitResult);
        }
        return new GuessResult(digitResults);
    }

    public GuessDigitResult[] getDigitResults() {
        return digitResults;
    }

    public GuessDigitResult resultAt(final int index) {
        return digitResults[index];
    }

}
