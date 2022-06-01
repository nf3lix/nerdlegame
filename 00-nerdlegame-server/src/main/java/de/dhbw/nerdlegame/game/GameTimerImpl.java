package de.dhbw.nerdlegame.game;

import de.dhbw.nerdlegame.GameTimer;

public class GameTimerImpl implements GameTimer {
    @Override
    public long currentTimeInMillis() {
        return System.currentTimeMillis();
    }
}
