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
    public void onWaitingForClients() {
        NerdleGameServer.logMessage("Waiting for players...");
    }

    @Override
    public void onClientConnected() {
        final PlayerName playerName = nextPlayerName();
        NerdleGameServer.logMessage(playerName + " joined the game");
        nerdleGame.registerPlayer(new Player(nextPlayerId(), playerName));
    }

    private PlayerId nextPlayerId() {
        return new PlayerId(UUID.randomUUID());
    }

    private PlayerName nextPlayerName() {
        return new PlayerName("Player" + (nerdleGame.currentPlayerCount() + 1));
    }

}
