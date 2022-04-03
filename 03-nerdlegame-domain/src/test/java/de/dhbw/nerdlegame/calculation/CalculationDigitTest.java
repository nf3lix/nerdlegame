package de.dhbw.nerdlegame.calculation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class CalculationDigitTest {

    @Test
    public void getCalculationDigitFromNumber() {
        final Iterator<CalculationDigit> iterator = Arrays.stream(CalculationDigit.values()).collect(Collectors.toList()).iterator();
        for(int digit = 0; digit < 10; digit++) {
            assertEquals(CalculationDigit.getDigit(String.valueOf(digit).charAt(0)), iterator.next());
        }
    }

    @Test
    public void getCalculationDigitFromOperator() {
        assertEquals(CalculationDigit.getDigit('+'), CalculationDigit.PLUS);
        assertEquals(CalculationDigit.getDigit('-'), CalculationDigit.MINUS);
        assertEquals(CalculationDigit.getDigit('*'), CalculationDigit.TIMES);
        assertEquals(CalculationDigit.getDigit('/'), CalculationDigit.DIVIDED);
    }

}
