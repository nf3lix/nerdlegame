package de.dhbw.nerdlegame.player;

import java.util.Objects;
import java.util.UUID;

public final class PlayerId {

    private final UUID id;

    public PlayerId(final UUID id) {
        this.id = id;
    }

    public PlayerId(final String id) {
        this.id = UUID.fromString(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerId playerId = (PlayerId) o;
        return Objects.equals(id, playerId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
