package de.dhbw.nerdlegame.calculation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CalculationTest {

    @Test
    public void calculationsAreEqualWhenDigitsAreEqual() {
        assertEquals(new Calculation("23+45=68"), new Calculation("23+45=68"));
        assertEquals(new Calculation("7*6+1=43"), new Calculation("7*6+1=43"));
        assertEquals(new Calculation("99-21=78"), new Calculation("99-21=78"));
    }

    @Test
    public void calculationsAreUnequalWhenDigitsAreUnequal() {
        assertNotEquals(new Calculation("23+45=68"), new Calculation("7*6+1=43"));
        assertNotEquals(new Calculation("7*6+1=43"), new Calculation("99-21=78"));
        assertNotEquals(new Calculation("99-21=78"), new Calculation("23+45=68"));
    }

    @Test
    public void throwExceptionWhenLengthIsIncorrect() {
        assertThrows(IllegalArgumentException.class, () -> new Calculation("123+2=125"));
        assertThrows(IllegalArgumentException.class, () -> new Calculation("23+45=68="));
        assertThrows(IllegalArgumentException.class, () -> new Calculation("12+7=19"));
    }

    @Test
    public void throwExceptionWhenInputHasMoreThanOneEqualsSigns() {
        assertThrows(IllegalArgumentException.class, () -> new Calculation("23+45=68="));
        assertThrows(IllegalArgumentException.class, () -> new Calculation("4=2*2=4"));
    }

    @Test
    public void throwExceptionWhenCalculationIsIncorrect() {
        assertThrows(IllegalArgumentException.class, () -> new Calculation("23+45=67"));
    }

}
