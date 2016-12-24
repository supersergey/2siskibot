
package ua.kiev.supersergey.siski_bot.service.storage;

import com.google.cloud.datastore.*;
import ua.kiev.supersergey.siski_bot.entity.Update;
import ua.kiev.supersergey.siski_bot.entity.UpdateBody;
import ua.kiev.supersergey.siski_bot.entity.UserDTO;
import ua.kiev.supersergey.siski_bot.entity.UserDTO.*;
import ua.kiev.supersergey.siski_bot.entity.constants.StringMessages;

import java.util.*;

/**
 * Created by sergey on 08.12.2016.
 */
public class StorageServiceImpl implements StorageService {
    private static final long ONE_DAY = 60 * 60 * 24 * 1000;
    private static final long ONE_WEEK = 60 * 60 * 24 * 7;
    private static final long TWO_WEEKS = 60 * 60 * 24 * 14;
    private static final long ONE_MONTH = 60 * 60 * 30 * 14;
    public static final String USER_NEW_KIND = "user_new";
    //    private Datastore datastore;
    private Datastore datastoreNew;
    //    private KeyFactory keyFactory;
    private KeyFactory keyFactoryNew;

    public void init() {
        if (datastoreNew == null || keyFactoryNew == null) {
//            this.datastore = DatastoreOptions.getDefaultInstance().getService();
            this.datastoreNew = DatastoreOptions.getDefaultInstance().getService();
//            this.keyFactory = datastore.newKeyFactory().setKind("user");
            this.keyFactoryNew = datastoreNew.newKeyFactory().setKind(USER_NEW_KIND);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        UpdateBody updateBody = (UpdateBody) arg;
        if (!updateBody.isNotifyMessage() && !updateBody.hasCallback()) {
            add(UserDTOFactory.getUserDTO((UpdateBody) arg));
        }
    }

    @Override
    public Key add(UserDTO user) {
        init();
        Key key = getKey(user.getId());
        Entity userEntity = Entity.newBuilder(key)
                .set("first_name", user.getFirstName())
                .set("last_name", user.getLastName())
                .set("message_id", user.getMessageId())
                .set("message_text", user.getMessageText())
                .set("created", user.getDate())
                .build();
        datastoreNew.put(userEntity);
        return userEntity.getKey();
    }

    @Override
    public List<UserDTO> getAll(int days) {
        init();
        Query q = buildGetQuery(days);
        QueryResults<Entity> queryResults = datastoreNew.run(q);
        List<UserDTO> users = new ArrayList<>();
        while (queryResults.hasNext()) {
            Entity e = queryResults.next();
            users.add(UserDTOFactory.getUserFromEntity(e));
        }
        return users;
    }

    private Query buildGetQuery(int days) {
        if (days > 0) {
            Date current = new Date();
            return Query.newEntityQueryBuilder()
                    .setKind(USER_NEW_KIND)
                    .setFilter(
                            StructuredQuery.CompositeFilter.and(
                                    StructuredQuery.PropertyFilter.ge("created", current.getTime() - (days * 60L * 60L * 24L * 1000L + ONE_DAY)),
                                    StructuredQuery.PropertyFilter.le("created", current.getTime() - (days * 60L * 60L * 24L * 1000L))))
                    .setOrderBy(StructuredQuery.OrderBy.asc("created"))
                    .build();
        } else {
            return Query.newEntityQueryBuilder()
                    .setKind(USER_NEW_KIND)
                    .setOrderBy(StructuredQuery.OrderBy.asc("created"))
                    .build();
        }
    }

    private Key getKey(long userId) {
        Entity entity = datastoreNew.get(keyFactoryNew.newKey(userId));
        Key key;
        if (entity != null) {
            key = entity.getKey();
        } else {
            key = keyFactoryNew.newKey(userId);
        }
        return key;
    }

    public void updateDb() {
//        init();
//        ProjectionEntityQuery q = Query.newProjectionEntityQueryBuilder()
//                .setKind("user")
//                .setDistinctOn("user_id")
//                .setOrderBy(StructuredQuery.OrderBy.asc("user_id"), StructuredQuery.OrderBy.asc("created"))
//                .build();
//        QueryResults<ProjectionEntity> queryResults = datastore.run(q);
//        while (queryResults.hasNext()) {
//            ProjectionEntity e = queryResults.next();
//            UserDTO user = UserDTOFactory.getUserFromEntity(e);
//            IncompleteKey key = keyFactoryNew.newKey(user.getId());
//            FullEntity<IncompleteKey> fullEntity = Entity.newBuilder(key)
//                    .set("first_name", user.getFirstName())
//                    .set("last_name", user.getLastName())
//                    .set("message_id", user.getMessageId())
//                    .set("message_text", user.getMessageText())
//                    .set("created", user.getDate())
//                    .build();
//            datastoreNew.put(fullEntity);
//        }
    }
}

