package de.dhbw.nerdlegame.calculation;

import de.dhbw.nerdlegame.expression.Expression;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class Calculation {

    public static final int NUMBER_OF_DIGITS = 8;
    private final CalculationDigit[] digits = new CalculationDigit[NUMBER_OF_DIGITS];

    public Calculation(final String input) {
        final char[] chars = input.toCharArray();
        if(chars.length != NUMBER_OF_DIGITS) {
            throw new IllegalArgumentException("Invalid number of digits. Must be " + NUMBER_OF_DIGITS);
        }
        if(input.split("=").length != 2) {
            throw new IllegalArgumentException("Term must be of the form <term>=<number>");
        }
        final int result;
        try {
            result = Integer.parseInt(input.split("=")[1]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Term must be of the form <term>=<number>");
        }
        if(result != new Expression(input.split("=")[0]).result()) {
            throw new IllegalArgumentException("Input is no valid mathematical expression");
        }
        for(int charIndex = 0; charIndex < NUMBER_OF_DIGITS; charIndex++) {
            digits[charIndex] = CalculationDigit.getDigit(chars[charIndex]);
        }
    }

    public CalculationDigit getDigit(final int id) {
        return digits[id];
    }

    public boolean contains(final CalculationDigit digit) {
        return occurrencesOf(digit) >= 1;
    }

    public int occurrencesOf(final CalculationDigit digit) {
        final List<CalculationDigit> occurrences = Arrays.stream(digits).filter(actualDigit -> actualDigit == digit).collect(Collectors.toList());
        return occurrences.size();
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        for(CalculationDigit digit : digits) {
            builder.append(digit.name());
        }
        return builder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Calculation that = (Calculation) o;
        return Arrays.equals(digits, that.digits);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(digits);
    }

}
