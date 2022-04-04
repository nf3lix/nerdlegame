package de.dhbw.nerdlegame.calculation;

import java.util.Arrays;

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
        if(result != evaluateExpression(input.split("=")[0])) {
            throw new IllegalArgumentException("Input is no valid mathematical expression");
        }
        for(int charIndex = 0; charIndex < NUMBER_OF_DIGITS; charIndex++) {
            digits[charIndex] = CalculationDigit.getDigit(chars[charIndex]);
        }
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

    private static double evaluateExpression(final String str) {
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char)ch);
                return x;
            }

            double parseExpression() {
                double x = parseTerm();
                for (;;) {
                    if      (eat('+')) x += parseTerm(); // addition
                    else if (eat('-')) x -= parseTerm(); // subtraction
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (;;) {
                    if      (eat('*')) x *= parseFactor(); // multiplication
                    else if (eat('/')) x /= parseFactor(); // division
                    else return x;
                }
            }

            double parseFactor() {
                if (eat('+')) return +parseFactor(); // unary plus
                if (eat('-')) return -parseFactor(); // unary minus

                double x;
                int startPos = this.pos;
                if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                } else {
                    throw new RuntimeException("Unexpected: " + (char)ch);
                }

                return x;
            }
        }.parse();
    }

}
