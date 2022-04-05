package de.dhbw.nerdlegame;

import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class SocketObserverTest {

    @Test
    public void test() {
        final NerdleGame nerdleGame = mock(NerdleGame.class);
        final ConcreteSocketObserver concreteSocketObserver = new ConcreteSocketObserver(nerdleGame);
        concreteSocketObserver.onClientConnected();
        verify(nerdleGame).registerPlayer(any());
    }

}
