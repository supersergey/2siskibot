package ua.kiev.supersergey.siski_bot.reply_manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import ua.kiev.supersergey.siski_bot.entity.UpdateBody;
import ua.kiev.supersergey.siski_bot.entity.constants.StringMessages;
import ua.kiev.supersergey.siski_bot.rest.RestService;

/**
 * Created by sergey on 30.11.2016.
 */
@Component
public class ReplyService implements Runnable {
    @Autowired
    private RestService restService;

    public ReplyService() {
    }

    @Override
    public void run() {
        UpdateBody updateBody = ReplyQueue.getNext();
        if (updateBody.getCallBackQuery() != null) {
            restService.replyToCallBackQuery(updateBody);
        } else {
            String command = updateBody.getMessage().getText();
            doReply(updateBody, command);
        }
    }

    private void doReply(UpdateBody updateBody, String command) {
        if (StringUtils.isEmpty(command)) {
            return;
        }
        switch (command) {
            case StringMessages.START:
                restService.sendStartMessage(updateBody);
                break;
            case StringMessages.SEND_ME_SISKI:
                restService.sendPhoto(updateBody);
                break;
            default:
                restService.sendUnknownMessage(updateBody);
                break;
        }
    }
}
