package ua.kiev.supersergey.siski_bot.reply;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import ua.kiev.supersergey.siski_bot.entity.UpdateBody;
import ua.kiev.supersergey.siski_bot.entity.constants.StringMessages;
import ua.kiev.supersergey.siski_bot.rest.RestService;
import ua.kiev.supersergey.siski_bot.rest.RestServiceImpl;

/**
 * Created by sergey on 30.11.2016.
 */
@Component
public class ReplyServiceImpl implements ReplyService {
    private RestService restServiceImpl;

    public ReplyServiceImpl(RestService restServiceImpl) {
        this.restServiceImpl = restServiceImpl;
    }

    @Override
    public void run() {
        UpdateBody updateBody = ReplyQueue.getNext();
        if (updateBody.getCallBackQuery() != null) {
            restServiceImpl.replyToCallBackQuery(updateBody);
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
                restServiceImpl.sendStartMessage(updateBody);
                break;
            case StringMessages.SEND_ME_SISKI:
                restServiceImpl.sendPhoto(updateBody);
                break;
            default:
                restServiceImpl.sendUnknownMessage(updateBody);
                break;
        }
    }
}
