package de.dhbw.nerdlegame;

import de.dhbw.nerdlegame.player.Player;
import de.dhbw.nerdlegame.player.PlayerId;
import de.dhbw.nerdlegame.player.PlayerName;

import java.util.UUID;

public class SocketConnectionObserverImpl implements ServerConnectionObserver {

    private NerdleGame nerdleGame;

    public SocketConnectionObserverImpl(final NerdleGame nerdleGame) {
        this.nerdleGame = nerdleGame;
    }

    @Override
    public void onClientConnected() {
        nerdleGame.registerPlayer(new Player(nextPlayerId(), nextPlayerName()));
    }

    private PlayerId nextPlayerId() {
        return new PlayerId(UUID.randomUUID());
    }

    private PlayerName nextPlayerName() {
        return new PlayerName("Player" + nerdleGame.currentPlayerCount());
    }

}
