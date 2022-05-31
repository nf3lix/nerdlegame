package de.dhbw.nerdlegame.ui;

import de.dhbw.nerdlegame.calculation.CalculationDigit;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.util.HashSet;
import java.util.Set;

public class CalculationDigitFilter extends DocumentFilter implements DigitTypedObservable {

    private final Set<DigitTypedObserver> observers = new HashSet<>();

    @Override
    public void replace(final FilterBypass fb, final int offset, final int length, final String text, final AttributeSet attrs) throws BadLocationException {
        if(offset != 0) {
            return;
        }
        if(text.length() == 1) {
            try {
                CalculationDigit.getDigit(text.charAt(0));
                super.replace(fb, offset, length, text, attrs);
                observers.forEach(DigitTypedObserver::onDigitTyped);
            } catch (IllegalArgumentException ignored) { }
        }
    }

    @Override
    public void addDigitTypedListener(final DigitTypedObserver observer) {
        this.observers.add(observer);
    }
}
