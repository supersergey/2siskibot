package ua.kiev.supersergey.siski_bot.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import ua.kiev.supersergey.siski_bot.entity.keyboard.AbstractKeyboard;
import ua.kiev.supersergey.siski_bot.entity.keyboard.Keyboard;
import ua.kiev.supersergey.siski_bot.entity.keyboard.StartKeyboard;

/**
 * Created by sergey on 30.11.2016.
 */
public class Reply {
    @JsonProperty("chat_id")
    private int chatId;
    private String text;
    @JsonProperty("reply_to_message_id")
    private int replyToMessageId;
    @JsonProperty("reply_markup")
    private AbstractKeyboard keyboard;

    public Reply() {
    }

    public Reply(int chatId, String text, int replyToMessageId, AbstractKeyboard keyboard) {
        this.chatId = chatId;
        this.text = text;
        this.replyToMessageId = replyToMessageId;
        this.keyboard = keyboard;
    }

    public Reply(int chatId, String text, AbstractKeyboard keyboard) {
        this.chatId = chatId;
        this.text = text;
        this.keyboard = keyboard;
    }

    public int getChatId() {
        return chatId;
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getReplyToMessageId() {
        return replyToMessageId;
    }

    public void setReplyToMessageId(int replyToMessageId) {
        this.replyToMessageId = replyToMessageId;
    }
}
