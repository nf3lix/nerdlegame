package de.dhbw.nerdlegame.message;

public class Message {

    private static final String separator = "\u0590";
    private final String messageType;
    private final String content;

    public Message(final String message) {
        final String[] keyValue = message.split(separator);
        if(keyValue.length != 2) {
            throw new MalformedMessage("Prefix and content must be separated by " + separator);
        }
        this.messageType = keyValue[0];
        this.content = keyValue[1];
    }

    public Message(final MessageType messageType, final Object content) {
        if(content.toString().contains(separator)) {
            throw new MalformedMessage("Content must not contain separator '" + separator + "'");
        }
        this.messageType = messageType.name();
        this.content = content.toString();
    }

    public MessageType getMessageType() {
        return MessageType.valueOf(messageType);
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return messageType + separator + content;
    }

}
