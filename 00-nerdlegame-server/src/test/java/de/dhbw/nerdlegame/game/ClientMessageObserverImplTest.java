package de.dhbw.nerdlegame.game;

import de.dhbw.nerdlegame.CalculationGenerator;
import de.dhbw.nerdlegame.GameTimer;
import de.dhbw.nerdlegame.NerdleGame;
import de.dhbw.nerdlegame.calculation.Calculation;
import de.dhbw.nerdlegame.game.ClientMessageObserverImpl;
import de.dhbw.nerdlegame.message.Message;
import de.dhbw.nerdlegame.message.MessageType;
import de.dhbw.nerdlegame.player.Player;
import de.dhbw.nerdlegame.server.ClientHandler;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ClientMessageObserverImplTest {

    @Test
    public void sendGuessResultToClientHandler() {
        final ClientHandler handler = mock(ClientHandler.class);
        final ClientMessageObserverImpl observer = prepareGame(handler);
        observer.onClientMessageReceived("guess 11+11=22");
        final ArgumentCaptor<Message> argumentCaptor = ArgumentCaptor.forClass(Message.class);
        verify(handler).sendMessage(argumentCaptor.capture());
        assertEquals(argumentCaptor.getValue().getMessageType(), MessageType.GUESS_RESULT);
    }

    @Test
    public void sendErrorMessageWhenGuessingTooFast() {
        final ClientHandler handler = mock(ClientHandler.class);
        final ClientMessageObserverImpl observer = prepareGame(handler);
        observer.onClientMessageReceived("guess 11+12=23");
        observer.onClientMessageReceived("guess 11+12=23");
        final ArgumentCaptor<Message> argumentCaptor = ArgumentCaptor.forClass(Message.class);
        verify(handler, times(2)).sendMessage(argumentCaptor.capture());
        assertEquals(argumentCaptor.getValue().getMessageType(), MessageType.TOO_LITTLE_TIME_SINCE_LAST_GUESS_ERROR);
    }

    @Test
    public void sendMessageOnGuessingWhenGameHasNotStartedYet() {
        final CalculationGenerator calculationGenerator = mock(CalculationGenerator.class);
        when(calculationGenerator.nextCalculation()).thenReturn(new Calculation("11+11=22"));
        final GameTimer gameTimer = mock(GameTimer.class);
        when(gameTimer.currentTimeInMillis()).thenReturn(1600000000L);
        final NerdleGame nerdleGame = new NerdleGame(calculationGenerator, gameTimer);
        final ClientHandler handler = mock(ClientHandler.class);
        final Player player = mock(Player.class);
        nerdleGame.registerPlayer(player);
        final ClientMessageObserverImpl observer = new ClientMessageObserverImpl(handler, player, nerdleGame);
        observer.onClientMessageReceived("guess 11+11=22");
        final ArgumentCaptor<Message> argumentCaptor = ArgumentCaptor.forClass(Message.class);
        verify(handler).sendMessage(argumentCaptor.capture());
        assertEquals(argumentCaptor.getValue().getMessageType(), MessageType.GUESSING_NOT_STARTED_YET);
    }

    private ClientMessageObserverImpl prepareGame(final ClientHandler clientHandler) {
        final CalculationGenerator calculationGenerator = mock(CalculationGenerator.class);
        when(calculationGenerator.nextCalculation()).thenReturn(new Calculation("11+11=22"));
        final GameTimer gameTimer = mock(GameTimer.class);
        when(gameTimer.currentTimeInMillis()).thenReturn(1600000000L);
        final NerdleGame nerdleGame = new NerdleGame(calculationGenerator, gameTimer);
        final Player player = mock(Player.class);
        nerdleGame.registerPlayer(player);
        for(int playerCount = 0; playerCount < NerdleGame.MAX_PLAYERS - 1; playerCount++) {
            nerdleGame.registerPlayer(mock(Player.class));
        }
        return new ClientMessageObserverImpl(clientHandler, player, nerdleGame);
    }

}
