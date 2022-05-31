package de.dhbw.nerdlegame.ui;

import de.dhbw.nerdlegame.calculation.CalculationDigit;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class CalculationDigitFilter extends DocumentFilter {

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        if(offset != 0) {
            return;
        }
        if(text.length() == 1) {
            try {
                CalculationDigit.getDigit(text.charAt(0));
                super.replace(fb, offset, length, text, attrs);
            } catch (IllegalArgumentException ignored) { }
        }
    }
}
