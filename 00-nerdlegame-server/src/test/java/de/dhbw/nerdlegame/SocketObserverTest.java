package de.dhbw.nerdlegame;

import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class SocketObserverTest {

    @Test
    public void test() {
        final NerdleGame nerdleGame = mock(NerdleGame.class);
        final SocketConnectionObserverImpl concreteSocketObserver = new SocketConnectionObserverImpl(nerdleGame);
        concreteSocketObserver.onClientConnected();
        verify(nerdleGame).registerPlayer(any());
    }

}
