package de.dhbw.nerdlegame.calculation;

public enum CalculationDigit {

    ZERO    ('0'),
    ONE     ('1'),
    TWO     ('2'),
    THREE   ('3'),
    FOUR    ('4'),
    FIVE    ('5'),
    SIX     ('6'),
    SEVEN   ('7'),
    EIGHT   ('8'),
    NINE    ('9'),

    PLUS    ('+'),
    MINUS   ('-'),
    TIMES   ('*'),
    DIVIDED ('/'),
    EQUALS  ('=');

    private final char c;
    CalculationDigit(final char c) {
        this.c = c;
    }

    public static CalculationDigit getDigit(final char c) {
        for(CalculationDigit digit : CalculationDigit.values()) {
            if(digit.c == c) {
                return digit;
            }
        }
        throw new IllegalArgumentException("Invalid digit");
    }

}
