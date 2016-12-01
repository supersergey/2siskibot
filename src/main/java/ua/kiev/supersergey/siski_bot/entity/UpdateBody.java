package ua.kiev.supersergey.siski_bot.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by sergey on 29.11.2016.
 */
public class UpdateBody {
    @JsonProperty("update_id")
    private int updateId;
    private Message message;
    @JsonProperty("callback_query")
    private CallbackQuery callBackQuery;

    public UpdateBody() {
    }

    public int getUpdateId() {
        return updateId;
    }

    public void setUpdateId(int updateId) {
        this.updateId = updateId;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public CallbackQuery getCallBackQuery() {
        return callBackQuery;
    }

    public void setCallBackQuery(CallbackQuery callBackQuery) {
        this.callBackQuery = callBackQuery;
    }
}
