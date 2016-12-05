package ua.kiev.supersergey.siski_bot.controller;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.kiev.supersergey.siski_bot.entity.Update;
import ua.kiev.supersergey.siski_bot.entity.UpdateBody;
import ua.kiev.supersergey.siski_bot.reply_manager.ReplyManager;
import ua.kiev.supersergey.siski_bot.reply_manager.ReplyQueue;

import java.util.concurrent.Executor;

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

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public String test() {
        System.out.println("Hello");
        return "Hello worldzzzzzzzzzz 3";
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    @ResponseBody
    public void loadUpdate(@RequestParam(required = true) Update update) {
        for (UpdateBody e : update.getResult()) {
            ReplyQueue.addUpdate(e);
            executor.execute(replyManager);
            LOGGER.info("Received from " + e.getMessage().getFrom() + ": " + e.getMessage().getText());
        }
    }
}
