package de.dhbw.nerdlegame.mapper;

import de.dhbw.nerdlegame.guess.GuessResult;
import de.dhbw.nerdlegame.resource.GuessResultResource;

public class GuessResultMapper {

    public GuessResultResource toResource(final GuessResult guessResult) {
        return new GuessResultResource(guessResult);
    }

    public GuessResult toDomainModel(final GuessResultResource resource) {
        return new GuessResult(resource.getDigitResults());
    }

}
