package de.dhbw.nerdlegame.resource;

import de.dhbw.nerdlegame.guess.GuessResult;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class GuessResultResourceMatcher extends TypeSafeMatcher<GuessResult> {

    static GuessResultResourceMatcher hasStringRepresentation(final String representation) {
        return new GuessResultResourceMatcher(representation);
    }

    private final String representation;

    public GuessResultResourceMatcher(final String representation) {
        this.representation = representation;
    }

    @Override
    protected boolean matchesSafely(final GuessResult guessResult) {
        return new GuessResultResource(guessResult).toString().equals(representation);
    }

    @Override
    public void describeTo(final Description description) {
        description.appendText("has string representation ");
        description.appendValue(representation);
    }

    @Override
    protected void describeMismatchSafely(GuessResult item, Description mismatchDescription) {
        mismatchDescription.appendText("has string representation ");
        mismatchDescription.appendValue(new GuessResultResource(item).toString());
    }
}
