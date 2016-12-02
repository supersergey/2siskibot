package ua.kiev.supersergey.siski_bot.actions;

import ua.kiev.supersergey.siski_bot.entity.AnswerCallbackQuery;
import ua.kiev.supersergey.siski_bot.entity.AnswerCallbackQueryFactory;
import ua.kiev.supersergey.siski_bot.entity.Message;
import ua.kiev.supersergey.siski_bot.entity.UpdateBody;
import ua.kiev.supersergey.siski_bot.service.rest.RestService;

/**
 * Created by sergey on 01.12.2016.
 */
public class SendCallbackQuery implements SendAction{
    @Override
    public Message send(RestService restService, UpdateBody body) {
        AnswerCallbackQuery answerCallbackQuery = AnswerCallbackQueryFactory.getAnswerCallbackQuery(body.getCallBackQuery(), "Спасибо, твой голос засчитан!");
        restService.sendCallBackQuery(answerCallbackQuery);
        return new Message();
    }

}
