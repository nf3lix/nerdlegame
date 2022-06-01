package de.dhbw.nerdlegame.game;

import de.dhbw.nerdlegame.CalculationGenerator;
import de.dhbw.nerdlegame.NerdleGame;
import de.dhbw.nerdlegame.calculation.Calculation;
import de.dhbw.nerdlegame.game.GameQueue;
import de.dhbw.nerdlegame.message.Message;
import de.dhbw.nerdlegame.message.MessageType;
import de.dhbw.nerdlegame.server.ClientHandler;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GameQueueTest {

    @Test
    public void sendMessagesToClientsWhenGameStarts() {
        final CalculationGenerator calculationGenerator = mock(CalculationGenerator.class);
        when(calculationGenerator.nextCalculation()).thenReturn(new Calculation("11+11=22"));
        final GameQueue gameQueue = new GameQueue(calculationGenerator);
        final List<ClientHandler> clientHandlers = new ArrayList<>();
        for(int i = 0; i < NerdleGame.MAX_PLAYERS; i++) {
            final ClientHandler clientHandler = mock(ClientHandler.class);
            clientHandlers.add(clientHandler);
            gameQueue.onClientConnected(clientHandler);
        }
        for(final ClientHandler clientHandler : clientHandlers) {
            final ArgumentCaptor<Message> captor = ArgumentCaptor.forClass(Message.class);
            verify(clientHandler).sendMessage(captor.capture());
            assertEquals(captor.getValue().getMessageType(), MessageType.GAME_STARTS);
        }
    }

}
