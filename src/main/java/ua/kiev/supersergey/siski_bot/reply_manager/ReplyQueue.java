package ua.kiev.supersergey.siski_bot.reply_manager;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import ua.kiev.supersergey.siski_bot.entity.UpdateBody;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by sergey on 30.11.2016.
 */
@Service
public class ReplyQueue {
    private static ConcurrentLinkedQueue<UpdateBody> pending;

    public static void addUpdate(UpdateBody updateBody) {
        if (pending == null) {
            pending = new ConcurrentLinkedQueue<>();
        }
        pending.add(updateBody);
    }

    public static UpdateBody getNext() {
        return pending.poll();
    }

}
