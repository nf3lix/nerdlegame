package de.dhbw.nerdlegame.guess;

import de.dhbw.nerdlegame.calculation.Calculation;
import de.dhbw.nerdlegame.player.Player;

import java.util.UUID;

public class Guess {

    private final UUID uuid;
    private final Player player;
    private final Calculation guess;

    public Guess(final UUID uuid, final Player player, final Calculation calculation) {
        this.uuid = uuid;
        this.player = player;
        this.guess = calculation;
    }

    public Player player() {
        return player;
    }

    public boolean matches(final Calculation calculation) {
        System.out.println(calculation().equals(calculation));
        return calculation().equals(calculation);
    }

    public Calculation calculation() {
        return guess;
    }

}
