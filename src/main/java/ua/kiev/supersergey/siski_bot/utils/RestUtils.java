package ua.kiev.supersergey.siski_bot.utils;

import org.springframework.http.HttpStatus;

/**
 * Created by sergey on 29.11.2016.
 */
public class RestUtils {
    public static boolean isError(HttpStatus status) {
        HttpStatus.Series series = status.series();
        return (HttpStatus.Series.CLIENT_ERROR.equals(series)
                || HttpStatus.Series.SERVER_ERROR.equals(series));
    }
}
