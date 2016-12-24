package ua.kiev.supersergey.siski_bot.entity;

/**
 * Created by sergey on 16.12.2016.
 */
public class UpdateBodyFactory {
    public static UpdateBody getFromUser(UserDTO userDTO) {
        UpdateBody result = new UpdateBody();
        result.setUpdateId(10000);
        Message message = new Message();
        // message.setMessageId(userDTO.getMessageId());
        message.setText(userDTO.getMessageText());
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setId(userDTO.getId());
        message.setFrom(user);
        result.setMessage(message);
        return result;
    }
}
