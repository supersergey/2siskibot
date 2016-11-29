package ua.kiev.supersergey.siski_bot.exception;

/**
 * Created by sergey on 29.11.2016.
 */
public class UpdateCommonException extends Exception {
    private int statusCode;
    public UpdateCommonException(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
