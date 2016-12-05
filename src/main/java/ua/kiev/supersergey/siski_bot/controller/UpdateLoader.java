package ua.kiev.supersergey.siski_bot.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.*;
import ua.kiev.supersergey.siski_bot.entity.UpdateBody;
import ua.kiev.supersergey.siski_bot.reply_manager.ReplyManager;
import ua.kiev.supersergey.siski_bot.reply_manager.ReplyQueue;

/**
 * Created by sergey on 02.12.2016.
 */
@RestController
public class UpdateLoader {
    private static Logger LOGGER = Logger.getLogger(UpdateLoader.class);
    @Autowired
    private ThreadPoolTaskExecutor executor;
    @Autowired
    private ReplyManager replyManager;

    @RequestMapping(value = "/1", method = RequestMethod.GET)
    @ResponseBody
    public String hello() {
        return "Вас приветствует сиськибот!!!!-4";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public void loadUpdate(@RequestBody(required = false) String update) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        UpdateBody updateBody = mapper.readValue(update, UpdateBody.class);
        System.out.println(updateBody);
        ReplyQueue.addUpdate(updateBody);
        replyManager.run();
        LOGGER.info("Received from " + updateBody.getMessage().getFrom() + ": " + updateBody.getMessage().getText());
    }
}
