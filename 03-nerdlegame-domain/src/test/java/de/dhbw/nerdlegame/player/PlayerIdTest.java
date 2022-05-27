package de.dhbw.nerdlegame.player;

import de.dhbw.nerdlegame.test_utils.ValueObjectBehaviorTest;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.function.Supplier;

public class PlayerIdTest {

    @Test
    public void playerIdEqualityByString() {
        final List<Supplier<PlayerId>> suppliers = Arrays.asList(
                () -> new PlayerId("569e2f72-f0f6-4a88-b701-af38e948742b"),
                () -> new PlayerId("d069e2cf-d02b-4d51-ac50-2946ae88c540"),
                () -> new PlayerId("eaa5773c-67f3-4b33-81b7-ebed40ea926e"),
                () -> new PlayerId("faa6361b-2587-4e24-a870-64b6e9c26a37")
        );
        ValueObjectBehaviorTest.withDisjointList(suppliers);
    }

    @Test
    public void playerIdEqualityByUUID() {
        final List<Supplier<PlayerId>> suppliers = Arrays.asList(
                () -> new PlayerId(UUID.fromString("569e2f72-f0f6-4a88-b701-af38e948742b")),
                () -> new PlayerId(UUID.fromString("d069e2cf-d02b-4d51-ac50-2946ae88c540")),
                () -> new PlayerId(UUID.fromString("eaa5773c-67f3-4b33-81b7-ebed40ea926e")),
                () -> new PlayerId(UUID.fromString("faa6361b-2587-4e24-a870-64b6e9c26a37"))
        );
        ValueObjectBehaviorTest.withDisjointList(suppliers);
    }

}
