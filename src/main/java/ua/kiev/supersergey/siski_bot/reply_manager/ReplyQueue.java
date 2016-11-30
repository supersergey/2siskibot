package ua.kiev.supersergey.siski_bot.reply_manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.kiev.supersergey.siski_bot.entity.UpdateBody;

import java.util.*;
import java.util.concurrent.Executor;

/**
 * Created by sergey on 30.11.2016.
 */
@Service
public class ReplyQueue {
    private static Queue<UpdateBody> pending;

    public ReplyQueue() {
        pending = new PriorityQueue<>((o1, o2) -> {
                return Integer.compare(o1.getUpdateId(), o2.getUpdateId());
            });
    }

    public static void addUpdate(UpdateBody updateBody) {
        pending.add(updateBody);
    }

    public static UpdateBody getNext() {
        return pending.poll();
    }
}
