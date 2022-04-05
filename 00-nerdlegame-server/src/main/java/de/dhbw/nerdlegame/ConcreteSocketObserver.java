package de.dhbw.nerdlegame;

import de.dhbw.nerdlegame.player.Player;

import java.util.UUID;

public class ConcreteSocketObserver implements SocketObserver {

    private NerdleGame nerdleGame;

    public ConcreteSocketObserver(final NerdleGame nerdleGame) {
        this.nerdleGame = nerdleGame;
    }

    @Override
    public void onClientConnected() {
        nerdleGame.registerPlayer(new Player(UUID.randomUUID(), "Player " + nerdleGame.currentPlayerCount()));
    }


}
