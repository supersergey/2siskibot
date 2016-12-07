package ua.kiev.supersergey.siski_bot.service.storage;

import com.google.cloud.datastore.*;
import ua.kiev.supersergey.siski_bot.entity.UserDTO;

/**
 * Created by sergey on 08.12.2016.
 */
public class StorageServiceImpl implements StorageService{
    private Datastore datastore;
    private KeyFactory keyFactory;

    public void init() {
        datastore = DatastoreOptions.getDefaultInstance().getService();
        keyFactory = datastore.newKeyFactory().setKind("user");
    }

    @Override
    public int addEntry(UserDTO user) {
        if (datastore == null || keyFactory == null) {
            init();
        }
        Key key = datastore.allocateId(keyFactory.newKey());
        Entity userEntity = Entity.newBuilder(key)
                .set("user_id", user.getId())
                .set("fist_name", user.getFirstName())
                .set("last_name", user.getLastName())
                .set("message_id", user.getMessageId())
                .set("message_text", user.getMessageText())
                .set("created", user.getDate())
                .build();
        datastore.put(userEntity);
        return user.getId();
    }
}
