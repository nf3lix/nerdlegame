package de.dhbw.nerdlegame;

public class GameTimerImpl implements GameTimer {
    @Override
    public long currentTimeInMillis() {
        return System.currentTimeMillis();
    }
}
