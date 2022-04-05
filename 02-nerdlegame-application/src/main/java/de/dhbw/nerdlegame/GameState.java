package de.dhbw.nerdlegame;

public enum GameState {

    WAIT_FOR_PLAYERS {
        @Override
        boolean hasNextState() {
            return true;
        }

        @Override
        GameState nextState() {
            return GameState.GUESSING;
        }
    },

    GUESSING {
        @Override
        boolean hasNextState() {
            return true;
        }

        @Override
        GameState nextState() {
            return GameState.FINISHED;
        }
    },

    FINISHED {
        @Override
        boolean hasNextState() {
            return false;
        }

        @Override
        GameState nextState() {
            return GameState.FINISHED;
        }
    };

    abstract boolean hasNextState();
    abstract GameState nextState();

}
