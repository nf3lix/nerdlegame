package de.dhbw.nerdlegame.server;

import de.dhbw.nerdlegame.NerdleGame;
import de.dhbw.nerdlegame.message.Message;
import de.dhbw.nerdlegame.message.MessageType;
import de.dhbw.nerdlegame.player.Player;

import java.util.Map;

public class Game {

    private final Map<ClientHandler, Player> clients;
    private final NerdleGame nerdleGame;

    public Game(final NerdleGame nerdleGame, final Map<ClientHandler, Player> clients) {
        this.nerdleGame = nerdleGame;
        this.nerdleGame.addWinnerDeterminedListener(player -> broadCastMessage(new Message(MessageType.PLAYER_WINS, player.playerName() + " guessed the calculation after " + nerdleGame.amountOfGuesses(player) + " guesses")));
        this.clients = clients;
    }

    public void broadCastMessage(final Message message) {
        clients.keySet().forEach(client -> client.sendMessage(message));
    }

}
