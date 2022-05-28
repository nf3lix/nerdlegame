package de.dhbw.nerdlegame.resource;

import de.dhbw.nerdlegame.calculation.Calculation;
import de.dhbw.nerdlegame.guess.GuessResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static de.dhbw.nerdlegame.resource.GuessResultResourceMatcher.hasStringRepresentation;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class GuessResultResourceTest {

    @Test
    public void toStringTest() {
        final GuessResult guessResult1 = GuessResult.createFromGuess(new Calculation("11+11=22"), new Calculation("11+11=22"));
        assertThat(guessResult1, hasStringRepresentation("ONE?CORRECT,ONE?CORRECT,PLUS?CORRECT,ONE?CORRECT,ONE?CORRECT,EQUALS?CORRECT,TWO?CORRECT,TWO?CORRECT,"));

        final GuessResult guessResult2 = GuessResult.createFromGuess(new Calculation("11+11=22"), new Calculation("33+33=66"));
        assertThat(guessResult2, hasStringRepresentation("THREE?NOT_IN_SOLUTION,THREE?NOT_IN_SOLUTION,PLUS?CORRECT,THREE?NOT_IN_SOLUTION,THREE?NOT_IN_SOLUTION,EQUALS?CORRECT,SIX?NOT_IN_SOLUTION,SIX?NOT_IN_SOLUTION,"));
    }

    @Test
    public void createFromString() {
        final String representation = "THREE?NOT_IN_SOLUTION,THREE?NOT_IN_SOLUTION,PLUS?CORRECT,THREE?NOT_IN_SOLUTION,THREE?NOT_IN_SOLUTION,EQUALS?CORRECT,SIX?NOT_IN_SOLUTION,SIX?NOT_IN_SOLUTION,";
        final GuessResultResource resource = new GuessResultResource(representation);
        assertEquals(resource.toString(), representation);
    }

}
