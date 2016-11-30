package ua.kiev.supersergey.siski_bot.entity.keyboard;

/**
 * Created by sergey on 30.11.2016.
 */
public class KeyboardButton {
    private String text;

    public KeyboardButton() {
    }

    public KeyboardButton(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
