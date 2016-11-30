package ua.kiev.supersergey.siski_bot.entity.keyboard;

/**
 * Created by sergey on 30.11.2016.
 */
public abstract class Keyboard extends AbstractKeyboard {
    protected KeyboardButton[][] keyboard;

    public KeyboardButton[][] getKeyboard() {
        return keyboard;
    }

    public void setKeyboard(KeyboardButton[][] keyboard) {
        this.keyboard = keyboard;
    }
}
