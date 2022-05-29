package de.dhbw.nerdlegame;

import de.dhbw.nerdlegame.calculation.Calculation;
import de.dhbw.nerdlegame.guess.Guess;
import de.dhbw.nerdlegame.guess.GuessResult;
import de.dhbw.nerdlegame.message.Message;
import de.dhbw.nerdlegame.message.MessageType;
import de.dhbw.nerdlegame.player.Player;
import de.dhbw.nerdlegame.resource.GuessResultResource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ServerConnectionObserverTest {

    @Test
    public void sendMessageWithGuessResultOnGuess() {
        final NerdleGame nerdleGame = new NerdleGame(calculationGenerator("12+35=47"));
        registerAllPlayers(nerdleGame);
        final ServerConnectionObserverImpl serverConnectionObserver = new ServerConnectionObserverImpl(nerdleGame);
        final Guess guess = new Guess(UUID.randomUUID(), mock(Player.class), new Calculation("12+34=46"));
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

}
