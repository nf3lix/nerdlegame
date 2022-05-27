package de.dhbw.nerdlegame.player;

import de.dhbw.nerdlegame.test_utils.ValueObjectBehaviorTest;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class PlayerTest {

    @Test
    public void playerObjectEqualityTest() {
        final List<Supplier<Player>> suppliers = Arrays.asList(
                () -> playerWithId(new PlayerId("d069e2cf-d02b-4d51-ac50-2946ae88c540")),
                () -> playerWithId(new PlayerId("eaa5773c-67f3-4b33-81b7-ebed40ea926e")),
                () -> playerWithId(new PlayerId("faa6361b-2587-4e24-a870-64b6e9c26a37"))
        );
        ValueObjectBehaviorTest.withDisjointList(suppliers);
    }

    private Player playerWithId(final PlayerId playerId) {
        return new Player(playerId, new PlayerName("PLAYERNAME"));
    }

}
