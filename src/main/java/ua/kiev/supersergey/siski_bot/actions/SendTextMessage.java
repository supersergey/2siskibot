package ua.kiev.supersergey.siski_bot.actions;

import ua.kiev.supersergey.siski_bot.entity.Message;
import ua.kiev.supersergey.siski_bot.entity.Reply;
import ua.kiev.supersergey.siski_bot.entity.UpdateBody;
import ua.kiev.supersergey.siski_bot.entity.keyboard.StartKeyboard;
import ua.kiev.supersergey.siski_bot.entity.ReplyFactory;
import ua.kiev.supersergey.siski_bot.service.rest.RestService;

/**
 * Created by sergey on 01.12.2016.
 */

public class SendTextMessage implements SendAction {
    private String text;

    public SendTextMessage(String text) {
        this.text = text;
    }

    @Override
    public Message send(RestService restService, UpdateBody body) {
        Reply reply = ReplyFactory.createReply(body, text, new StartKeyboard());
        return restService.send(reply, "/sendMessage");
    }
}
