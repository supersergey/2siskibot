package ua.kiev.supersergey.siski_bot.entity;

import org.joda.time.DateTime;

import java.util.Date;

/**
 * Created by sergey on 08.12.2016.
 */
public class UserDTOFactory {
    public static UserDTO getUserDTO(UpdateBody updateBody) {
        UserDTO result = new UserDTO();
        if (updateBody != null && updateBody.getMessage() != null) {
            result.setMessageId(updateBody.getMessage().getMessageId());
            result.setMessageText(updateBody.getMessage().getText());
            result.setDate(new Date().getTime());
            if (updateBody.getMessage().getFrom() != null) {
                result.setId(updateBody.getMessage().getFrom().getId());
                result.setFirstName(updateBody.getMessage().getFrom().getFirstName());
                result.setLastName(updateBody.getMessage().getFrom().getLastName());
            }
        }
        return result;
    }
}
