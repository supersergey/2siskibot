package ua.kiev.supersergey.siski_bot.updater;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ua.kiev.supersergey.siski_bot.entity.Update;
import ua.kiev.supersergey.siski_bot.exception.UpdateCommonException;
import ua.kiev.supersergey.siski_bot.utils.RestUtils;

import java.util.Collections;
import java.util.concurrent.Future;

/**
 * Created by sergey on 29.11.2016.
 */
@Service
public class UpdateChecker {
    private final static Logger LOGGER = Logger.getLogger(UpdateChecker.class);
    private final RestTemplate restTemplate = new RestTemplate();
    private final static String URL = "https://api.telegram.org/bot";
    @Value("${telegram.bot.token}")
    private String token;

    public UpdateChecker() {
    }

    @Async
    public Future<Update> checkForUpdate() throws InterruptedException, UpdateCommonException {
        ResponseEntity<Update> entity = restTemplate.getForEntity(URL + token + "/getUpdates", Update.class, Collections.emptyMap());
        if (RestUtils.isError(entity.getStatusCode())) {
            LOGGER.error(entity.getStatusCode().getReasonPhrase() + ":" + entity.getStatusCodeValue());
        }
        return new AsyncResult<>(entity.getBody());
    }
}
