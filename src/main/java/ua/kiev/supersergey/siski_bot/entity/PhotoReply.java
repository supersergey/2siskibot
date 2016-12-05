package ua.kiev.supersergey.siski_bot.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import ua.kiev.supersergey.siski_bot.entity.keyboard.AbstractKeyboard;
import ua.kiev.supersergey.siski_bot.entity.keyboard.Keyboard;

/**
 * Created by sergey on 30.11.2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PhotoReply extends Reply {
    @JsonProperty("photo")
    private String photo;
    private String caption = "Зацени сисечки, %s!";

    public PhotoReply(int chatId, String userName, AbstractKeyboard keyboard, String photoUrl) {
        super(chatId, userName, keyboard);
        caption = String.format(caption, userName);
        this.photo = photoUrl;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }
}
