package ua.kiev.supersergey.siski_bot.entity.keyboard;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by sergey on 30.11.2016.
 */
public class InlineKeyboardButton {
    private String text;
    @JsonProperty("callback_data")
    private String callbackData;

    public InlineKeyboardButton() {
    }

    public InlineKeyboardButton(String text, String callbackData) {
        this.text = text;
        this.callbackData = callbackData;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCallbackData() {
        return callbackData;
    }

    public void setCallbackData(String callbackData) {
        this.callbackData = callbackData;
    }
}
