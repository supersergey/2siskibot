package ua.kiev.supersergey.siski_bot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import ua.kiev.supersergey.siski_bot.entity.Update;
import ua.kiev.supersergey.siski_bot.entity.UpdateBody;
import ua.kiev.supersergey.siski_bot.updater.UpdateChecker;

import javax.annotation.PostConstruct;
import java.util.concurrent.Future;

/**
 * Created by sergey on 29.11.2016.
 */
@Component
public class AppRunner implements CommandLineRunner {
    @Autowired
    private UpdateChecker updateChecker;

    @Override
    public void run(String... strings) throws Exception {
        while (!Thread.currentThread().isInterrupted())
        {
            Future<Update> updateFuture = updateChecker.checkForUpdate();
            while (!updateFuture.isDone()) {
                Thread.sleep(10L);
            }
            Update update = updateFuture.get();
            if (update.getOk().equals("true")) {
                for (UpdateBody body : update.getResult()) {
                    System.out.println(body.getMessage().getText());
                }
            }
            Thread.sleep(10000L);
        }
    }
}
