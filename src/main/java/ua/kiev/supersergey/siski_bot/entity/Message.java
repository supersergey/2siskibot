package ua.kiev.supersergey.siski_bot.entity;

/**
 * Created by sergey on 29.11.2016.
 */
public class Message {
    private int message_id;
    private User from;
    private String text;

    public Message() {
    }

    public int getMessage_id() {
        return message_id;
    }

    public void setMessage_id(int message_id) {
        this.message_id = message_id;
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
