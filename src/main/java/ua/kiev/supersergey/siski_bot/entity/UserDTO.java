package ua.kiev.supersergey.siski_bot.entity;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by sergey on 08.12.2016.
 */
public class UserDTO {
    private long id;
    private String firstName;
    private String lastName;
    private long messageId;
    private String messageText;
    private long date;

    private final static SimpleDateFormat FORMATTER = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    public UserDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName == null ? "" : firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName == null ? "" : lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public long getMessageId() {
        return messageId;
    }

    public void setMessageId(long messageId) {
        this.messageId = messageId;
    }

    public String getMessageText() {
        return messageText == null ? "" : messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", messageId=" + messageId +
                ", messageText='" + messageText + '\'' +
                ", date=" + FORMATTER.format(new Date(date)) +
                '}';
    }
}
