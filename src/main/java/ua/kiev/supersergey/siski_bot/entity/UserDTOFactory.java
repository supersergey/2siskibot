package ua.kiev.supersergey.siski_bot.entity;

import com.google.cloud.datastore.BaseEntity;
import com.google.cloud.datastore.DatastoreException;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.ProjectionEntity;

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

    public static UserDTO getUserFromEntity(Entity e) {
        UserDTO result = new UserDTO();
        result.setId(e.getKey().getId());
        try {
            result.setFirstName(e.getString("first_name"));
        } catch (DatastoreException ex) {}
        try {
            result.setFirstName(e.getString("fist_name"));
        } catch (DatastoreException ex) {}
        result.setLastName(e.getString("last_name"));
        result.setMessageId(e.getLong("message_id"));
        result.setMessageText(e.getString("message_text"));
        result.setDate(e.getLong("created"));
        return result;
    }
}
