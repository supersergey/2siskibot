package ua.kiev.supersergey.siski_bot.service.storage;

import ua.kiev.supersergey.siski_bot.entity.UserDTO;

/**
 * Created by sergey on 08.12.2016.
 */
public interface StorageService {
    int addEntry(UserDTO user);
}
