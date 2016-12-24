package ua.kiev.supersergey.siski_bot.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import ua.kiev.supersergey.siski_bot.entity.keyboard.AbstractKeyboard;
import ua.kiev.supersergey.siski_bot.entity.keyboard.Keyboard;
import ua.kiev.supersergey.siski_bot.entity.keyboard.StartKeyboard;

/**
 * Created by sergey on 30.11.2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Reply {
    @JsonProperty("chat_id")
    private long chatId;
    private String text;
    @JsonProperty("reply_to_message_id")
    private long replyToMessageId;
    @JsonProperty("reply_markup")
    private AbstractKeyboard keyboard;

    public Reply() {
    }

    public Reply(long chatId, String text, long replyToMessageId, AbstractKeyboard keyboard) {
        this.chatId = chatId;
        this.text = text;
        this.replyToMessageId = replyToMessageId;
        this.keyboard = keyboard;
    }

    public Reply(long chatId, String text, AbstractKeyboard keyboard) {
        this.chatId = chatId;
        this.text = text;
        this.keyboard = keyboard;
    }

    public long getChatId() {
        return chatId;
    }

    public void setChatId(long chatId) {
        this.chatId = chatId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getReplyToMessageId() {
        return replyToMessageId;
    }

    public void setReplyToMessageId(long replyToMessageId) {
        this.replyToMessageId = replyToMessageId;
    }
}
