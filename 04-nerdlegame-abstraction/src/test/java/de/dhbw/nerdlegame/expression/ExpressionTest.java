package de.dhbw.nerdlegame.expression;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExpressionTest {

    @Test
    public void returnResultOfExpression() {
        final Expression expression1 = new Expression("1+1");
        final Expression expression2 = new Expression("2*42+3");
        final Expression expression3 = new Expression("3*12+6");
        assertEquals(expression1.result(), 2);
        assertEquals(expression2.result(), 87);
        assertEquals(expression3.result(), 42);
    }

}
