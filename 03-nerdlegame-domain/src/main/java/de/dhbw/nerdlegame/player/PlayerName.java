package de.dhbw.nerdlegame.player;

import java.util.Objects;

public class PlayerName {

    public static final String PLAYER_NAME_REGEX = "[A-Za-z0-9]{3,16}";
    private final String name;

    public PlayerName(final String name) {
        if(!name.matches(PLAYER_NAME_REGEX)) {
            throw new InvalidPlayerName("Player name must match " + PLAYER_NAME_REGEX);
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerName that = (PlayerName) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

}
