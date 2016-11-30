package ua.kiev.supersergey.siski_bot.entity.keyboard;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by sergey on 30.11.2016.
 */
public abstract class InlineKeyboard extends AbstractKeyboard {
    @JsonProperty("inline_keyboard")
    protected InlineKeyboardButton[][] keyboard;

    public InlineKeyboard() {
    }

    public InlineKeyboard(InlineKeyboardButton[][] keyboard) {
        this.keyboard = keyboard;
    }

    public InlineKeyboardButton[][] getKeyboard() {
        return keyboard;
    }

    public void setKeyboard(InlineKeyboardButton[][] keyboard) {
        this.keyboard = keyboard;
    }
}
