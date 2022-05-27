package de.dhbw.nerdlegame.guess;

import de.dhbw.nerdlegame.calculation.CalculationDigit;

public class GuessDigitResult {

    private final CalculationDigit guessedDigit;
    private final DigitResultType digitResult;

    public GuessDigitResult(final CalculationDigit guessedDigit, final DigitResultType digitResult) {
        this.guessedDigit = guessedDigit;
        this.digitResult = digitResult;
    }

    public CalculationDigit guessedDigit() {
        return guessedDigit;
    }

    public DigitResultType digitResult() {
        return digitResult;
    }

}
