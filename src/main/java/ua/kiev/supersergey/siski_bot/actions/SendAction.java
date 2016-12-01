package ua.kiev.supersergey.siski_bot.actions;

import ua.kiev.supersergey.siski_bot.entity.Message;
import ua.kiev.supersergey.siski_bot.entity.UpdateBody;
import ua.kiev.supersergey.siski_bot.service.rest.RestService;

/**
 * Created by sergey on 01.12.2016.
 */
public interface SendAction {
    Message send(RestService restService, UpdateBody body);
}
