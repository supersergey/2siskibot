package ua.kiev.supersergey.siski_bot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import ua.kiev.supersergey.siski_bot.reply_manager.ReplyManager;
import ua.kiev.supersergey.siski_bot.reply_manager.ReplyQueue;
import ua.kiev.supersergey.siski_bot.service.storage.StorageService;

@Component
public class MyInitializer implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private StorageService storageService;

    @Autowired
    private ReplyQueue replyQueue;

    @Autowired
    private ReplyManager replyManager;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        replyQueue.addObserver(replyManager);
        replyQueue.addObserver(storageService);
    }
}