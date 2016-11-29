package ua.kiev.supersergey.siski_bot.entity;

/**
 * Created by sergey on 29.11.2016.
 */
public class UpdateBody {
    private int update_id;
    private Message message;

    public UpdateBody() {
    }

    public int getUpdate_id() {
        return update_id;
    }

    public void setUpdate_id(int update_id) {
        this.update_id = update_id;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}
