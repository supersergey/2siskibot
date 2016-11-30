package ua.kiev.supersergey.siski_bot.entity.keyboard;

/**
 * Created by sergey on 30.11.2016.
 */
public class EmptyKeyboard extends Keyboard {
    public EmptyKeyboard() {
        keyboard = new KeyboardButton[0][0];
    }
}
