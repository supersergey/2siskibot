package ua.kiev.supersergey.siski_bot.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by sergey on 29.11.2016.
 */
public class Message {
    @JsonProperty("message_id")
    private int messageId;
    private User from;
    private String text;

    public Message() {
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public User getFrom() {
        return from;
    }

    public void setFrom(User from) {
        this.from = from;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
