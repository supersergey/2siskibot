package ua.kiev.supersergey.siski_bot.service.storage;

import com.google.appengine.api.datastore.Key;
import ua.kiev.supersergey.siski_bot.entity.UserDTO;

import java.util.List;

/**
 * Created by sergey on 08.12.2016.
 */
public interface StorageService {
    Key add(UserDTO user);

    List<UserDTO> getAll(int limit);
}
