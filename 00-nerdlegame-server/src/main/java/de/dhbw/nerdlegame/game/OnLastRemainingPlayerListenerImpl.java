package de.dhbw.nerdlegame.game;

import de.dhbw.nerdlegame.listeners.OnLastRemainingPlayerObserver;
import de.dhbw.nerdlegame.message.Message;
import de.dhbw.nerdlegame.message.MessageType;
import de.dhbw.nerdlegame.player.Player;
import de.dhbw.nerdlegame.server.ClientHandler;

import java.util.Map;

public class OnLastRemainingPlayerListenerImpl implements OnLastRemainingPlayerObserver {

    private final Map<ClientHandler, Player> clients;

    public OnLastRemainingPlayerListenerImpl(final Map<ClientHandler, Player> clients) {
        this.clients = clients;
    }

    @Override
    public void onLastRemainPlayer(Player player) {
        clients.keySet().forEach(client -> {
            if(clients.get(client) == player) {
                client.sendMessage(new Message(MessageType.PLAYER_WINS, "You are the last remaining player. You have won!"));
                return;
            }
            client.sendMessage(new Message(MessageType.PLAYER_WINS, player.playerName() + " has won since he is the last remaining player"));
        });
    }
}
