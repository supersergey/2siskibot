package ua.kiev.supersergey.siski_bot.actions;

import ua.kiev.supersergey.siski_bot.entity.Message;
import ua.kiev.supersergey.siski_bot.entity.Reply;
import ua.kiev.supersergey.siski_bot.entity.UpdateBody;
import ua.kiev.supersergey.siski_bot.entity.keyboard.InlineRatingKeyboard;
import ua.kiev.supersergey.siski_bot.entity.ReplyFactory;
import ua.kiev.supersergey.siski_bot.service.rest.RestService;

/**
 * Created by sergey on 01.12.2016.
 */
public class SendPhoto implements SendAction {
    private String url;
    private String photoName;

    public SendPhoto(String url, String photoName) {
        this.url = url;
        this.photoName = photoName;
    }
     public Message send(RestService restService, UpdateBody body) {
        Reply reply = ReplyFactory.createPhotoReply(body, url + "/" + photoName, new InlineRatingKeyboard(photoName));
        return restService.send(reply, "/sendPhoto");
    }
}
