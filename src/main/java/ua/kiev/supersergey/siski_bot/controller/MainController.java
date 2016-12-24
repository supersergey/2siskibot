package ua.kiev.supersergey.siski_bot.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.kiev.supersergey.siski_bot.entity.UpdateBody;
import ua.kiev.supersergey.siski_bot.entity.UpdateBodyFactory;
import ua.kiev.supersergey.siski_bot.entity.UserDTO;
import ua.kiev.supersergey.siski_bot.entity.constants.StringMessages;
import ua.kiev.supersergey.siski_bot.reply_manager.ReplyQueue;
import ua.kiev.supersergey.siski_bot.service.images.ImageLoaderService;
import ua.kiev.supersergey.siski_bot.service.storage.StorageService;

import java.util.List;

/**
 * Created by sergey on 02.12.2016.
 */
@RestController
public class MainController {
    @Autowired
    private ReplyQueue replyQueue;
    @Autowired
    private ImageLoaderService imageLoader;
    @Autowired
    private StorageService storageService;

    @RequestMapping(value = "/images/load/", method = RequestMethod.GET)
    @ResponseBody
    public String loadImages() throws Exception {
        return String.format("Total images: %d", imageLoader.loadImages());
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public void loadUpdate(@RequestBody(required = false) String update) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        UpdateBody updateBody = mapper.readValue(update, UpdateBody.class);
        if (updateBody != null) {
            replyQueue.addUpdate(updateBody);
        }
    }

    @RequestMapping(value = "/stats", method = RequestMethod.GET)
    @ResponseBody
    public List<UserDTO> loadStats() throws Exception {
        return storageService.getAll(-1);
    }

    @RequestMapping(value = "/notify", method = RequestMethod.GET)
    @ResponseBody
    public List<UserDTO> loadStats(@RequestParam(required = true) int days) throws Exception {
        List<UserDTO> users = storageService.getAll(days);
        for (UserDTO user : users) {
            user.setMessageText(StringMessages.NOTIFY);
            replyQueue.addUpdate(UpdateBodyFactory.getFromUser(user));
        }
        return users;
    }

    @RequestMapping(value = "/dbupdate", method = RequestMethod.GET)
    @ResponseBody
    public void updateDb() {
        //storageService.updateDb();
    }
}
