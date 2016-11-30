package ua.kiev.supersergey.siski_bot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ua.kiev.supersergey.siski_bot.entity.Update;
import ua.kiev.supersergey.siski_bot.entity.UpdateBody;
import ua.kiev.supersergey.siski_bot.reply_manager.ReplyQueue;
import ua.kiev.supersergey.siski_bot.reply_manager.ReplyService;
import ua.kiev.supersergey.siski_bot.rest.RestService;

import java.util.concurrent.Executor;
import java.util.concurrent.Future;

/**
 * Created by sergey on 29.11.2016.
 */
@Component
public class AppRunner implements CommandLineRunner {
    @Autowired
    private RestService restService;
    @Autowired
    private Executor executor;
    @Autowired
    private ReplyService replyService;

    private int offset = 0;

    @Override
    public void run(String... strings) throws Exception {
        while (!Thread.currentThread().isInterrupted())
        {
            Future<Update> updateFuture = restService.getUpdates(offset);
            while (!updateFuture.isDone()) {
                Thread.sleep(10L);
            }
            Update update = updateFuture.get();
            if (update.getOk().equals("true")) {
                for (UpdateBody body : update.getResult()) {
                    offset = body.getUpdateId() + 1;
                    ReplyQueue.addUpdate(body);
                    executor.execute(replyService);
                }
            }
            Thread.sleep(1000L);
        }
    }
}
