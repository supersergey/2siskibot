package ua.kiev.supersergey.siski_bot.entity;

import ua.kiev.supersergey.siski_bot.entity.PhotoReply;
import ua.kiev.supersergey.siski_bot.entity.Reply;
import ua.kiev.supersergey.siski_bot.entity.UpdateBody;
import ua.kiev.supersergey.siski_bot.entity.keyboard.AbstractKeyboard;

/**
 * Created by sergey on 01.12.2016.
 */
public class ReplyFactory {
    public static Reply createReply(UpdateBody body, String messageText, AbstractKeyboard keyboard) {
        int id = body.getMessage().getFrom().getId();
        String firstName = body.getMessage().getFrom().getFirstName();
        int messageId = body.getMessage().getMessageId();
        return new Reply(id, String.format(messageText, firstName), messageId, keyboard);
    }

    public static Reply createPhotoReply(UpdateBody body, String photoUrl, AbstractKeyboard keyboard) {
        int id = body.getMessage().getFrom().getId();
        String firstName = body.getMessage().getFrom().getFirstName();
        return new PhotoReply(id, firstName, keyboard, photoUrl);
    }
}
