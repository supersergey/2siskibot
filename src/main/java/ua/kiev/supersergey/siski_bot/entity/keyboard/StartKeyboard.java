package ua.kiev.supersergey.siski_bot.entity.keyboard;

import ua.kiev.supersergey.siski_bot.entity.constants.StringMessages;

/**
 * Created by sergey on 30.11.2016.
 */
public class StartKeyboard extends Keyboard {
    public StartKeyboard() {
        keyboard = new KeyboardButton[1][1];
        KeyboardButton button = new KeyboardButton(StringMessages.SEND_ME_SISKI);
        keyboard[0][0] = button;
    }
}
