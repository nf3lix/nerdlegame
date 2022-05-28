package de.dhbw.nerdlegame.message;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MessageTest {

    @Test
    public void createFromPrefixAndContent() {
        final Message message = new Message("MESSAGE", "CONTENT");
        assertEquals(message.toString(), "MESSAGE\u0590CONTENT");
    }

    @Test
    public void throwExceptionWhenMessageContainsSeparator() {
        assertThrows(MalformedMessage.class, () -> new Message("MESSAGE\u0590", "CONTENT"));
        assertThrows(MalformedMessage.class, () -> new Message("MESSAGE", "CONTENT\u0590"));
    }

    @Test
    public void createMessageObjectFromString() {
        final Message message = new Message("MESSAGE\u0590CONTENT");
        assertEquals(message.getPrefix(), "MESSAGE");
        assertEquals(message.getContent(), "CONTENT");
    }

    @Test
    public void throwExceptionWhenRawMessageDoesNotContainSeparator() {
        assertThrows(MalformedMessage.class, () -> new Message("MESSAGE_CONTENT"));
    }

    @Test
    public void throwExceptionWhenRawMessageContainMultipleSeparators() {
        assertThrows(MalformedMessage.class, () -> new Message("MESSAGE\u0590_\u0590CONTENT"));
        assertThrows(MalformedMessage.class, () -> new Message("\u0590MESSAGE\u0590CONTENT"));
    }

}
