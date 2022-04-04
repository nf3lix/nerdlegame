package de.dhbw.nerdlegame.expression;

/**
 * Evaluate mathematical expression with basic arithmetic operations and without parentheses.
 * source: <a href="https://stackoverflow.com/questions/3422673/how-to-evaluate-a-math-expression-given-in-string-form/26227947#26227947">stackoverflow</a>
 */
public class Expression {

    private final String input;
    private final double result;
    private int pos = -1, ch;


    /**
     * @param input mathematical expression as string
     */
    public Expression(final String input) {
        this.input = input;
        this.result = parse();
    }

    /**
     * @return result of mathematical expression
     */
    public double result() {
        return this.result;
    }

    private void nextChar() {
        ch = (++pos < input.length()) ? input.charAt(pos) : -1;
    }

    private boolean eat(int charToEat) {
        while (ch == ' ') nextChar();
        if (ch == charToEat) {
            nextChar();
            return true;
        }
        return false;
    }

    private double parse() {
        nextChar();
        double x = parseExpression();
        if (pos < input.length()) throw new RuntimeException("Unexpected: " + (char)ch);
        return x;
    }

    private double parseExpression() {
        double x = parseTerm();
        for (;;) {
            if      (eat('+')) x += parseTerm(); // addition
            else if (eat('-')) x -= parseTerm(); // subtraction
            else return x;
        }
    }

    private double parseTerm() {
        double x = parseFactor();
        for (;;) {
            if      (eat('*')) x *= parseFactor(); // multiplication
            else if (eat('/')) x /= parseFactor(); // division
            else return x;
        }
    }

    private double parseFactor() {
        if (eat('+')) return +parseFactor(); // unary plus
        if (eat('-')) return -parseFactor(); // unary minus

        double x;
        int startPos = this.pos;
        if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
            while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
            x = Double.parseDouble(input.substring(startPos, this.pos));
        } else {
            throw new RuntimeException("Unexpected: " + (char)ch);
        }

        return x;
    }

}
