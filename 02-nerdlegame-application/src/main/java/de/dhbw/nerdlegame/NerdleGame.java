package de.dhbw.nerdlegame;

import de.dhbw.nerdlegame.player.Player;

import java.util.ArrayList;
import java.util.List;

public class NerdleGame {

    public static final int MAX_PLAYERS = 2;
    private final List<Player> players = new ArrayList<>();
    private GameState gameState = GameState.WAIT_FOR_PLAYERS;

    public void registerPlayer(final Player player) {
        if(gameState != GameState.WAIT_FOR_PLAYERS) {
            throw new GameStateException("Players can only be registered in " + GameState.WAIT_FOR_PLAYERS.name() + " state. Current state is " + gameState.name());
        }
        players.add(player);
        if(players.size() == MAX_PLAYERS) {
            gameState = gameState.nextState();
        }
    }

}
