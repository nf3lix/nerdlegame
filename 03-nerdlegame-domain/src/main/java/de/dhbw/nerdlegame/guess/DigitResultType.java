package de.dhbw.nerdlegame.guess;

import de.dhbw.nerdlegame.calculation.Calculation;

public enum DigitResultType {

    CORRECT {
        @Override
        boolean applies(Calculation calculation, Calculation guessed, int id) {
            return calculation.getDigit(id) == guessed.getDigit(id);
        }
    },

    WRONG_SPOT {
        @Override
        boolean applies(Calculation calculation, Calculation guessed, int id) {
            if(calculation.getDigit(id) == guessed.getDigit(id)) {
                return false;
            }
            int actualOccurrences = calculation.occurrencesOf(guessed.getDigit(id));
            int guessedOccurrences = countOccurrencesOfDigitUpTo(guessed, id);
            return guessedOccurrences <= actualOccurrences;
        }

        private int countOccurrencesOfDigitUpTo(Calculation guessed, final int id) {
            int guessedOccurrences = 0;
            for(int indexFromStart = 0; indexFromStart <= id; indexFromStart++) {
                if(guessed.getDigit(indexFromStart) == guessed.getDigit(id)) {
                    guessedOccurrences++;
                }
            }
            return guessedOccurrences;
        }

    },

    NOT_IN_SOLUTION {
        @Override
        boolean applies(Calculation calculation, Calculation guessed, int id) {
            return !calculation.contains(guessed.getDigit(id));
        }
    };

    abstract boolean applies(final Calculation calculation, final Calculation guessed, int id);

    public static DigitResultType getDigitResult(final Calculation calculation, final Calculation guessed, int id) {
        for(final DigitResultType digitResult : DigitResultType.values()) {
            if(digitResult.applies(calculation, guessed, id)) {
                return digitResult;
            }
        }
        return DigitResultType.NOT_IN_SOLUTION;
    }

}
