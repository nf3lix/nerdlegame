package de.dhbw.nerdlegame.player;

import de.dhbw.nerdlegame.test_utils.ValueObjectBehaviorTest;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class PlayerNameTest {

    @Test
    public void throwExceptionWhenPlayerNameIsTooShort() {
        assertThrows(InvalidPlayerName.class, () -> new PlayerName(""));
        assertThrows(InvalidPlayerName.class, () -> new PlayerName("A"));
        assertThrows(InvalidPlayerName.class, () -> new PlayerName("AA"));
    }

    @Test
    public void throwExceptionWhenPlayerNameIsTooLong() {
        assertThrows(InvalidPlayerName.class, () -> new PlayerName("AAAAAAAAAAAAAAAAA"));
    }

    @Test
    public void throwExceptionWhenPlayerNameContainsInvalidChars() {
        assertThrows(InvalidPlayerName.class, () -> new PlayerName("____"));
    }

    @Test
    public void testPlayerNameEquality() {
        final List<Supplier<PlayerName>> suppliers = Arrays.asList(
                () -> new PlayerName("PLAYERNAME1"),
                () -> new PlayerName("PLAYERNAME2"),
                () -> new PlayerName("PLAYERNAME3")
        );
        ValueObjectBehaviorTest.withDisjointList(suppliers);
    }
}
