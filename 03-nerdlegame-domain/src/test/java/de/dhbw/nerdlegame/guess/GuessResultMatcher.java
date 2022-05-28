package de.dhbw.nerdlegame.guess;

import de.dhbw.nerdlegame.calculation.Calculation;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static de.dhbw.nerdlegame.guess.DigitResultType.*;

public class GuessResultMatcher extends TypeSafeMatcher<GuessResult> {

    static GuessResultMatcher hasCorrectDigitsAt(int... ids) {
        return new GuessResultMatcher(CORRECT, ids);
    }

    static GuessResultMatcher hasWrongSpottedDigitsAt(int... ids) {
        return new GuessResultMatcher(WRONG_SPOT, ids);
    }

    static GuessResultMatcher hasNotContainedDigitsAt(int... ids) {
        return new GuessResultMatcher(NOT_IN_SOLUTION, ids);
    }

    private final DigitResultType digitResult;
    private final List<Integer> matchingIds = new ArrayList<>();

    private GuessResultMatcher(final DigitResultType digitResult, int... matchingIds) {
        this.digitResult = digitResult;
        Arrays.stream(matchingIds).forEach(this.matchingIds::add);
    }

    @Override
    protected boolean matchesSafely(final GuessResult guessResult) {
        final List<Integer> actualMatchingIds = new ArrayList<>();
        for(int index = 0; index < Calculation.NUMBER_OF_DIGITS; index++) {
            if(guessResult.resultAt(index).resultType() == digitResult) {
                actualMatchingIds.add(index);
            }
        }
        return equalToExpectedIds(actualMatchingIds);
    }

    private boolean equalToExpectedIds(final List<Integer> actualMatchingIds) {
        if(actualMatchingIds.size() != matchingIds.size()) {
            return false;
        }
        for(int index = 0; index < actualMatchingIds.size(); index++) {
            if(!actualMatchingIds.get(index).equals(matchingIds.get(index))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("has " + digitResult.name() + " at ");
        for(final int id : matchingIds) {
            description.appendValue(id);
        }
    }

    @Override
    protected void describeMismatchSafely(GuessResult item, Description mismatchDescription) {
        mismatchDescription.appendText("has " + digitResult.name() + " at ");
        for(int index = 0; index < Calculation.NUMBER_OF_DIGITS; index++) {
            if(item.resultAt(index).resultType() == digitResult) {
                mismatchDescription.appendValue(index);
            }
        }
    }
}
