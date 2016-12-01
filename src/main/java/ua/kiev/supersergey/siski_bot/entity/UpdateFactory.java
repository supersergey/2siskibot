package ua.kiev.supersergey.siski_bot.entity;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by sergey on 01.12.2016.
 */
public class UpdateFactory {
    public static Update extractUpdate(Future<Update> future) throws ExecutionException, InterruptedException {
        while (!future.isDone()) {
            Thread.sleep(10L);
        }
        return future.get();
    }
}
