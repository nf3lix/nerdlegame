package de.dhbw.nerdlegame.server;

import de.dhbw.nerdlegame.calculation.Calculation;
import de.dhbw.nerdlegame.guess.Guess;
import de.dhbw.nerdlegame.guess.GuessResult;
import de.dhbw.nerdlegame.message.Message;
import de.dhbw.nerdlegame.message.MessageType;
import de.dhbw.nerdlegame.player.Player;
import de.dhbw.nerdlegame.player.PlayerId;
import de.dhbw.nerdlegame.resource.GuessResultResource;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ServerConnectionObserverTest {

    /*
    @Test
    public void sendMessageWithGuessResultOnGuess() {
        final NerdleGame nerdleGame = new NerdleGame(calculationGenerator("12+35=47"), gameTimer());
        final Player player = playerWithId("cb500c98-c898-45be-b4e2-df826cb55653");
        nerdleGame.registerPlayer(player);
        for(int playerCount = 0; playerCount < NerdleGame.MAX_PLAYERS - 1; playerCount++) {
            nerdleGame.registerPlayer(mock(Player.class));
        }
        final ServerConnectionObserverImpl serverConnectionObserver = new ServerConnectionObserverImpl(nerdleGame);

        final Guess guess = new Guess(UUID.randomUUID(), player, new Calculation("12+34=46"));
        final Receiver receiver = mock(Receiver.class);
        serverConnectionObserver.onGuess(receiver, guess);
        final ArgumentCaptor<Message> captor = ArgumentCaptor.forClass(Message.class);
        final GuessResult guessResult = GuessResult.createFromGuess(new Calculation("12+35=47"), new Calculation("12+34=46"));
        verify(receiver).sendMessage(captor.capture());
        assertEquals(captor.getValue().getMessageType(), MessageType.GUESS_RESULT);
        assertEquals(captor.getValue().getContent(), new GuessResultResource(guessResult).toString());
    }

    private CalculationGenerator calculationGenerator(final String calculation) {
        final CalculationGenerator generator = mock(CalculationGenerator.class);
        when(generator.nextCalculation()).thenReturn(new Calculation(calculation));
        return generator;
    }

    private void registerAllPlayers(final NerdleGame nerdleGame) {
        for(int playerCount = 0; playerCount < NerdleGame.MAX_PLAYERS; playerCount++) {
            nerdleGame.registerPlayer(mock(Player.class));
        }
    }

    private GameTimer gameTimer() {
        final GameTimer timer = mock(GameTimer.class);
        when(timer.currentTimeInMillis()).thenReturn(1600000000000L);
        return timer;
    }

    private Player playerWithId(final String id) {
        final Player player = mock(Player.class);
        when(player.playerId()).thenReturn(new PlayerId(id));
        return player;
    }*/

}
