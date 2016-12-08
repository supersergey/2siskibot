package ua.kiev.supersergey.siski_bot.reply_manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import ua.kiev.supersergey.siski_bot.actions.SendCallbackQuery;
import ua.kiev.supersergey.siski_bot.actions.SendPhoto;
import ua.kiev.supersergey.siski_bot.actions.SendTextMessage;
import ua.kiev.supersergey.siski_bot.entity.UpdateBody;
import ua.kiev.supersergey.siski_bot.entity.UserDTOFactory;
import ua.kiev.supersergey.siski_bot.entity.constants.StringMessages;
import ua.kiev.supersergey.siski_bot.service.images.ImageLoaderService;
import ua.kiev.supersergey.siski_bot.service.rating.RatingService;
import ua.kiev.supersergey.siski_bot.service.rest.RestService;
import ua.kiev.supersergey.siski_bot.service.storage.StorageService;

/**
 * Created by sergey on 30.11.2016.
 */
@Component
public class ReplyManager implements Runnable {
    @Autowired
    private RestService restService;
    @Autowired
    private ImageLoaderService imageLoaderService;
    @Autowired
    private RatingService ratingService;
    @Autowired
    private StorageService storageService;

    @Override
    public void run() {
        UpdateBody updateBody = ReplyQueue.getNext();
        if (updateBody != null) {
            if (updateBody.getCallBackQuery() != null) {
                ratingService.addRating(updateBody);
                new SendCallbackQuery().send(restService, updateBody);
            } else {
                String command = updateBody.getMessage().getText();
                doReply(updateBody, command);
                storageService.add(UserDTOFactory.getUserDTO(updateBody));
            }
        }
    }

    private void doReply(UpdateBody updateBody, String command) {
        if (StringUtils.isEmpty(command)) {
            return;
        }
        command = command.trim();
        if (command.equalsIgnoreCase(StringMessages.START)) {
            new SendTextMessage(StringMessages.START_TEXT).send(restService, updateBody);
            return;
        }
        if (command.equalsIgnoreCase(StringMessages.HELP)) {
            new SendTextMessage(StringMessages.HELP_TEXT).send(restService, updateBody);
            return;
        }
        if (command.toLowerCase().contains(StringMessages.SISKI)) {
            String url = imageLoaderService.getPhotoUrl();
            String photoName = imageLoaderService.getRandomImage();
            new SendPhoto(url, photoName).send(restService, updateBody);
            return;
        }
        new SendTextMessage(StringMessages.UNKNOWN_COMMAND_TEXT).send(restService, updateBody);
    }
}
