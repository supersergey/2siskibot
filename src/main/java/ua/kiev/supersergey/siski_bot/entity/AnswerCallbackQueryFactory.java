package ua.kiev.supersergey.siski_bot.entity;

/**
 * Created by sergey on 02.12.2016.
 */
public class AnswerCallbackQueryFactory {
    public static AnswerCallbackQuery getAnswerCallbackQuery(CallbackQuery query, String text) {
        String id = query.getId();
        return new AnswerCallbackQuery(id, text);
    }
}

