package de.dhbw.nerdlegame.resource;

import de.dhbw.nerdlegame.calculation.CalculationDigit;
import de.dhbw.nerdlegame.guess.DigitResultType;
import de.dhbw.nerdlegame.guess.GuessDigitResult;
import de.dhbw.nerdlegame.guess.GuessResult;

import java.util.Arrays;

public class GuessResultResource {

    private final static String DIGIT_SEPARATOR = ",";
    private final static String DIGIT_VALUE_SEPARATOR = "\\?";

    private final GuessDigitResult[] digitResults;

    public GuessResultResource(final String representation) {
        final String[] digits = representation.split(DIGIT_SEPARATOR);
        final GuessDigitResult[] digitResults = new GuessDigitResult[digits.length];
        for(int indexOfDigit = 0; indexOfDigit < digits.length; indexOfDigit++) {
            final String[] value = digits[indexOfDigit].split(DIGIT_VALUE_SEPARATOR);
            final CalculationDigit digit = CalculationDigit.valueOf(value[0]);
            final DigitResultType type = DigitResultType.valueOf(value[1]);
            digitResults[indexOfDigit] = new GuessDigitResult(digit, type);
        }
        this.digitResults = digitResults;
    }

    public GuessResultResource(final GuessResult guessResult) {
        this.digitResults = guessResult.getDigitResults();
    }

    public GuessDigitResult[] getDigitResults() {
        return digitResults;
    }

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();
        Arrays.stream(digitResults).forEach(digitResult -> stringBuilder
                .append(digitResult.guessedDigit())
                .append("?").append(digitResult.resultType())
                .append(","));
        return stringBuilder.toString();
    }

}
