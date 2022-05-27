package de.dhbw.nerdlegame.player;

import java.util.Objects;

public class Player {

    private final PlayerId playerId;
    private final PlayerName playerName;

    public Player(final PlayerId playerId, final PlayerName playerName) {
        this.playerId = playerId;
        this.playerName = playerName;
    }

    public PlayerId playerId() {
        return playerId;
    }

    public PlayerName playerName() {
        return playerName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(playerId, player.playerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerId);
    }

}
