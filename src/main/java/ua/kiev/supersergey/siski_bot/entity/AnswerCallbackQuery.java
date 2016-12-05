package ua.kiev.supersergey.siski_bot.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by sergey on 30.11.2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AnswerCallbackQuery {
    @JsonProperty("callback_query_id")
    private String id;
    private String text;

    public AnswerCallbackQuery() {
    }

    public AnswerCallbackQuery(String id, String text) {
        this.id = id;
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
