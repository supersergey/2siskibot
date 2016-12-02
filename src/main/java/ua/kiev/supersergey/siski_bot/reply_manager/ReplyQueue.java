package ua.kiev.supersergey.siski_bot.reply_manager;

import org.springframework.stereotype.Service;
import ua.kiev.supersergey.siski_bot.entity.UpdateBody;

import java.util.*;

/**
 * Created by sergey on 30.11.2016.
 */
@Service
public class ReplyQueue {
    private static Queue<UpdateBody> pending;

    public ReplyQueue() {
        pending = new PriorityQueue<UpdateBody>(100, new Comparator<UpdateBody>() {
            @Override
            public int compare(UpdateBody o1, UpdateBody o2) {
                return Integer.compare(o1.getUpdateId(), o2.getUpdateId());
            }
        });
    }

    public static void addUpdate(UpdateBody updateBody) {
        pending.add(updateBody);
    }

    public static UpdateBody getNext() {
        return pending.poll();
    }

}
