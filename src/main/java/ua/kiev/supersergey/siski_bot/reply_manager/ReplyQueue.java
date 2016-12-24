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
public class ReplyQueue extends Observable{
    public void addUpdate(UpdateBody updateBody) {
        this.setChanged();
        notifyObservers(updateBody);
    }
}
