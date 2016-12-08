package ua.kiev.supersergey.siski_bot.service.storage;

import com.google.appengine.api.datastore.*;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import ua.kiev.supersergey.siski_bot.entity.UserDTO;
import ua.kiev.supersergey.siski_bot.entity.UserDTOFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sergey on 08.12.2016.
 */
public class StorageServiceImpl implements StorageService{
    private DatastoreService datastore;

    public void init() {
        this.datastore = DatastoreServiceFactory.getDatastoreService();
    }

    @Override
    public Key add(UserDTO user) {
        if (datastore == null) {
            init();
        }
        Entity userEntity = new Entity("user");
        userEntity.setProperty("user_id", user.getId());
        userEntity.setProperty("first_name", user.getFirstName());
        userEntity.setProperty("last_name", user.getLastName());
        userEntity.setProperty("message_id", user.getMessageId());
        userEntity.setProperty("message_text", user.getMessageText());
        userEntity.setProperty("created", user.getDate());
        datastore.put(userEntity);
        return userEntity.getKey();
    }

    @Override
    public List<UserDTO> getAll(int limit) {
        if (datastore == null) {
            init();
        }
        Query q = new Query("user").addSort("created", Query.SortDirection.DESCENDING);
        PreparedQuery pq = datastore.prepare(q);
        List<UserDTO> users = new ArrayList<>();
        for (Entity e : pq.asIterable()) {
            users.add(UserDTOFactory.getUserFromEntity(e));
        }
        return users;
    }
}
