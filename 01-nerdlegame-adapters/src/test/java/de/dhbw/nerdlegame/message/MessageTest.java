package de.dhbw.nerdlegame.message;

import org.junit.jupiter.api.Test;

import static de.dhbw.nerdlegame.message.MessageType.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MessageTest {

    @Test
    public void createFromPrefixAndContent() {
        final Message message = new Message(PLAYER_WINS, "CONTENT");
        assertEquals(message.toString(), PLAYER_WINS.name() + "~CONTENT");
    }

    @Test
    public void throwExceptionWhenMessageContainsSeparator() {
        assertThrows(MalformedMessage.class, () -> new Message(PLAYER_WINS, "CONTENT~"));
    }

    @Test
    public void createMessageObjectFromString() {
        final Message message = new Message(PLAYER_WINS.name() + "~CONTENT");
        assertEquals(message.getMessageType(), PLAYER_WINS);
        assertEquals(message.getContent(), "CONTENT");
    }

    @Test
    public void throwExceptionWhenRawMessageDoesNotContainSeparator() {
        assertThrows(MalformedMessage.class, () -> new Message("MESSAGE_CONTENT"));
    }

    @Test
    public void throwExceptionWhenRawMessageContainMultipleSeparators() {
        assertThrows(MalformedMessage.class, () -> new Message("MESSAGE~_~CONTENT"));
        assertThrows(MalformedMessage.class, () -> new Message("~MESSAGE~CONTENT"));
    }

}
