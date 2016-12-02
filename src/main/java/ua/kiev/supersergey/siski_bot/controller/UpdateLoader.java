package ua.kiev.supersergey.siski_bot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.kiev.supersergey.siski_bot.entity.Update;

/**
 * Created by sergey on 02.12.2016.
 */
@RestController
public class UpdateLoader {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getHello() {
        return "Hello worlds";
    }

    @RequestMapping(value = "/update/send", method = RequestMethod.GET)
    public String getUpdates() {
        return "Hello worlds";
    }
}
