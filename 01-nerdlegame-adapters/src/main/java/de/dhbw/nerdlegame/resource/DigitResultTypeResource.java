package de.dhbw.nerdlegame.resource;

import de.dhbw.nerdlegame.guess.DigitResultType;
import de.dhbw.nerdlegame.guess.GuessDigitResult;

import java.awt.*;

public enum DigitResultTypeResource {
    CORRECT(DigitResultType.CORRECT) {
        @Override
        public Color color() {
            return new Color(120, 250, 90);
        }
    },
    WRONG_SPOT(DigitResultType.WRONG_SPOT) {
        @Override
        public Color color() {
            return new Color(105, 50, 245);
        }
    },
    NOT_IN_SOLUTION(DigitResultType.NOT_IN_SOLUTION) {
        @Override
        public Color color() {
            return new Color(120, 120, 120);
        }
    };

    private final DigitResultType resultType;

    DigitResultTypeResource(final DigitResultType resultType) {
        this.resultType = resultType;
    }

    public abstract Color color();

    public static DigitResultTypeResource fromDigitResult(final GuessDigitResult guessDigitResult) {
        for(final DigitResultTypeResource typeResource : DigitResultTypeResource.values()) {
            if(typeResource.resultType == guessDigitResult.resultType()) {
                return typeResource;
            }
        }
        throw new IllegalArgumentException();
    }

}
