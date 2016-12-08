package ua.kiev.supersergey.siski_bot.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.*;
import ua.kiev.supersergey.siski_bot.entity.UpdateBody;
import ua.kiev.supersergey.siski_bot.entity.UserDTO;
import ua.kiev.supersergey.siski_bot.reply_manager.ReplyManager;
import ua.kiev.supersergey.siski_bot.reply_manager.ReplyQueue;
import ua.kiev.supersergey.siski_bot.service.images.ImageLoaderService;
import ua.kiev.supersergey.siski_bot.service.storage.StorageService;

import java.util.List;

/**
 * Created by sergey on 02.12.2016.
 */
@RestController
public class UpdateLoader {
    private static Logger LOGGER = Logger.getLogger(UpdateLoader.class);
    @Autowired
    private ReplyManager replyManager;
    @Autowired
    private ImageLoaderService imageLoader;
    @Autowired
    private StorageService storageService;

    @RequestMapping(value = "/images/load", method = RequestMethod.GET)
    @ResponseBody
    public String loadImages() throws Exception {
        return String.format("Total images: %d", imageLoader.loadImages());
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public void loadUpdate(@RequestBody(required = false) String update) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        UpdateBody updateBody = mapper.readValue(update, UpdateBody.class);
        if (updateBody != null && updateBody.getMessage() != null) {
            ReplyQueue.addUpdate(updateBody);
            replyManager.run();
            LOGGER.info("Received from " + updateBody.getMessage().getFrom() + ": " + updateBody.getMessage().getText());
        }
    }

    @RequestMapping(value = "/stats/load", method = RequestMethod.GET)
    @ResponseBody
    public List<UserDTO> loadStats(@RequestParam(required = false, defaultValue = "0") int limit) throws Exception {
        return storageService.getAll(limit);
    }
}
