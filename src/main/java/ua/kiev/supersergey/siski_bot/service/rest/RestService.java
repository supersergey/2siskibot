package ua.kiev.supersergey.siski_bot.service.rest;

import org.springframework.http.ResponseEntity;
import ua.kiev.supersergey.siski_bot.entity.*;

import java.util.concurrent.Future;

/**
 * Created by sergey on 01.12.2016.
 */
public interface RestService {
    Future<Update> getUpdates(int offset);
    Message send(Reply reply, String command);
    Object sendCallBackQuery(AnswerCallbackQuery query);
}
