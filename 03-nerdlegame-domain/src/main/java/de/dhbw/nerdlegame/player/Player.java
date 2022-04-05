package de.dhbw.nerdlegame.player;

import java.util.Objects;
import java.util.UUID;

public class Player {

    private final UUID uuid;
    private final String name;

    public Player(final UUID uuid, final String name) {
        this.uuid = uuid;
        this.name = name;
    }

    public String name() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(uuid, player.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }

}
