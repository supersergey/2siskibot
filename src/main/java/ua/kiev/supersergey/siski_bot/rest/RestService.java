package ua.kiev.supersergey.siski_bot.rest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import ua.kiev.supersergey.siski_bot.entity.*;
import ua.kiev.supersergey.siski_bot.entity.constants.StringMessages;
import ua.kiev.supersergey.siski_bot.entity.keyboard.*;
import ua.kiev.supersergey.siski_bot.images_provider.ImageLoaderService;

import java.util.Collections;
import java.util.concurrent.Future;

/**
 * Created by sergey on 29.11.2016.
 */
@Component
public class RestService {
    private final static Logger LOGGER = Logger.getLogger(RestService.class);
    public static final RestTemplate REST_TEMPLATE = new RestTemplate();
    public final String URL = "https://api.telegram.org/bot";
    @Value("${telegram.bot.token}")
    private String token;

    @Autowired
    private ImageLoaderService provider;

    public RestService() {
    }

    @Async
    public Future<Update> getUpdates(int offset) {
        ResponseEntity<Update> entity = REST_TEMPLATE.getForEntity(URL + token + "/getUpdates?offset=" + offset, Update.class, Collections.emptyMap());
        logIfError(entity);
        return new AsyncResult<>(entity.getBody());
    }

    @Async
    public Message sendStartMessage(UpdateBody body) {
        Reply reply = createReply(body, StringMessages.START_TEXT, new StartKeyboard());
        return send(reply, "/sendMessage");
    }

    @Async
    public Message sendUnknownMessage(UpdateBody body) {
        Reply reply = createReply(body, StringMessages.UNKNOWN_COMMAND_TEXT, new EmptyKeyboard());
        return send(reply, "/sendMessage");
    }

    @Async
    public Message sendPhoto(UpdateBody body) {
        String photoUrl = provider.getRandomImage();
        Reply reply = createPhotoReply(body, photoUrl, new InlineRatingKeyboard(photoUrl));
        return send(reply, "/sendPhoto");
    }

    public void replyToCallBackQuery(UpdateBody updateBody) {
        System.out.println("Callback received");
    }

    @Async
    private Message send(Reply reply, String command) {
        try {
            ResponseEntity<Message> responseEntity = REST_TEMPLATE.postForEntity(URL + token + command, reply, Message.class);
            if (!logIfError(responseEntity)) {
                return responseEntity.getBody();
            } else {
                return null;
            }
        }
        catch (HttpClientErrorException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private Reply createPhotoReply(UpdateBody body, String photoUrl, AbstractKeyboard keyboard) {
        int id = body.getMessage().getFrom().getId();
        String firstName = body.getMessage().getFrom().getFirstName();
        return new PhotoReply(id, firstName, keyboard, photoUrl);
    }

    private Reply createReply(UpdateBody body, String messageText, AbstractKeyboard keyboard) {
        int id = body.getMessage().getFrom().getId();
        String firstName = body.getMessage().getFrom().getFirstName();
        int messageId = body.getMessage().getMessageId();
        return new Reply(id, String.format(messageText, firstName), messageId, keyboard);
    }

    private boolean logIfError(ResponseEntity entity) {
        if (isError(entity.getStatusCode())) {
            LOGGER.error(entity.getStatusCode().getReasonPhrase() + ":" + entity.getStatusCodeValue());
            return true;
        } else {
            return false;
        }
    }

    public static boolean isError(HttpStatus status) {
        HttpStatus.Series series = status.series();
        return (HttpStatus.Series.CLIENT_ERROR.equals(series)
                || HttpStatus.Series.SERVER_ERROR.equals(series));
    }


}
