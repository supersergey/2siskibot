package ua.kiev.supersergey.siski_bot.util;

import org.springframework.http.HttpStatus;

/**
 * Created by sergey on 01.12.2016.
 */
public class RestUtils {
    public static boolean isError(HttpStatus status) {
        HttpStatus.Series series = status.series();
        return (HttpStatus.Series.CLIENT_ERROR.equals(series)
                || HttpStatus.Series.SERVER_ERROR.equals(series));
    }
}
