package ua.kiev.supersergey.siski_bot.entity.keyboard;

/**
 * Created by sergey on 30.11.2016.
 */
public class InlineRatingKeyboard extends InlineKeyboard {
    public InlineRatingKeyboard(String photoUrl) {
        keyboard = new InlineKeyboardButton[1][5];
        for (int i = 0; i < 5; i++) {
            keyboard[0][i] = new InlineKeyboardButton(String.valueOf(i + 1), photoUrl + ":::" + String.valueOf(i));
        }
    }
}
