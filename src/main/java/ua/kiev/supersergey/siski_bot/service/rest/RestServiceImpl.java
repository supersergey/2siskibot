package ua.kiev.supersergey.siski_bot.service.rest;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import ua.kiev.supersergey.siski_bot.entity.AnswerCallbackQuery;
import ua.kiev.supersergey.siski_bot.entity.Message;
import ua.kiev.supersergey.siski_bot.entity.Reply;
import ua.kiev.supersergey.siski_bot.entity.Update;
import ua.kiev.supersergey.siski_bot.util.RestUtils;

import java.util.Collections;
import java.util.concurrent.Future;

/**
 * Created by sergey on 29.11.2016.
 */
public class RestServiceImpl implements RestService {
    private final static Logger LOGGER = Logger.getLogger(RestServiceImpl.class);
    public static final RestTemplate REST_TEMPLATE = new RestTemplate();
    private String url;
    private String token;

    public RestServiceImpl(String url, String token) {
        this.url = url;
        this.token = token;
    }

    @Async
    @Override
    public Future<Update> getUpdates(int offset) throws RestClientException {
        try {
            ResponseEntity<Update> entity = REST_TEMPLATE.getForEntity(url + token + "/getUpdates?offset=" + offset, Update.class, Collections.emptyMap());
            return new AsyncResult<>(extractResult(entity));
        } catch (HttpClientErrorException ex) {
            LOGGER.error(ex.getResponseBodyAsString());
            ex.printStackTrace();
            return null;
        }
    }

    @Async
    @Override
    public Message send(Reply reply, String command) {
        try {
            ResponseEntity<Message> responseEntity = REST_TEMPLATE.postForEntity(url + token + command, reply, Message.class);
            return extractResult(responseEntity);
        } catch (HttpClientErrorException ex) {
            LOGGER.error(ex.getResponseBodyAsString());
            ex.printStackTrace();
            return null;
        }
    }

    @Async
    @Override
    public Object sendCallBackQuery(AnswerCallbackQuery query) {
        try {
            ResponseEntity<Object> responseEntity = REST_TEMPLATE.postForEntity(url + token + "/answerCallbackQuery", query, Object.class);
            return extractResult(responseEntity);
        } catch (HttpClientErrorException ex) {
            LOGGER.error(ex.getResponseBodyAsString());
            ex.printStackTrace();
            return false;
        }
    }

    private <T> T extractResult(ResponseEntity<T> entity) {
        if (RestUtils.isError(entity.getStatusCode())) {
            LOGGER.error(entity.getStatusCode().getReasonPhrase() + ":" + entity.getStatusCodeValue());
            return null;
        } else {
            return entity.getBody();
        }
    }
}
