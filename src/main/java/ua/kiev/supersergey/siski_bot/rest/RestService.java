package ua.kiev.supersergey.siski_bot.rest;

import org.springframework.scheduling.annotation.Async;
import ua.kiev.supersergey.siski_bot.entity.Message;
import ua.kiev.supersergey.siski_bot.entity.Update;
import ua.kiev.supersergey.siski_bot.entity.UpdateBody;

import java.util.concurrent.Future;

/**
 * Created by sergey on 01.12.2016.
 */
public interface RestService {
    Future<Update> getUpdates(int offset);
    Message sendStartMessage(UpdateBody body);
    Message sendUnknownMessage(UpdateBody body);
    Message sendPhoto(UpdateBody body);
    void replyToCallBackQuery(UpdateBody updateBody);
}
