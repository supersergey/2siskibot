package ua.kiev.supersergey.siski_bot.runner;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ua.kiev.supersergey.siski_bot.entity.Update;
import ua.kiev.supersergey.siski_bot.entity.UpdateBody;
import ua.kiev.supersergey.siski_bot.reply_manager.ReplyManager;
import ua.kiev.supersergey.siski_bot.reply_manager.ReplyQueue;

import java.util.concurrent.Executor;

/**
 * Created by sergey on 29.11.2016.
 */
@Component
public class AppRunner implements CommandLineRunner {
    private final static Logger LOGGER = Logger.getLogger(AppRunner.class);

    @Autowired
    private Executor executor;
    @Autowired
    private ReplyManager replyManager;
    private int offset = 0;

    @Override
    public void run(String... strings) throws Exception {
        while (!Thread.currentThread().isInterrupted())
        {
            Update update = replyManager.loadUpdates(offset);
            if (update.getStatus().equals("true")) {
                executeUpdates(update);
            }
            else {
                LOGGER.error(update.getStatus());
            }
            Thread.sleep(1000L);
        }
    }

    private void executeUpdates(Update update) {
        for (UpdateBody body : update.getResult()) {
            offset = body.getUpdateId() + 1;
            ReplyQueue.addUpdate(body);
            executor.execute(replyManager);
        }
    }
}
