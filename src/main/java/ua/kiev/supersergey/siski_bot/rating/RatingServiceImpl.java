package ua.kiev.supersergey.siski_bot.rating;

import org.springframework.stereotype.Component;
import ua.kiev.supersergey.siski_bot.entity.CallBackQuery;
import ua.kiev.supersergey.siski_bot.entity.UpdateBody;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sergey on 01.12.2016.
 */
@Component
public class RatingServiceImpl implements RatingService {
    private Map<String, Integer> rating;

    @PostConstruct
    private void init() {
        rating = new HashMap<>();
    }

    @Override
    public void addRating(UpdateBody updateBody) {
        CallBackQuery callBackQuery = updateBody.getCallBackQuery();
        if (callBackQuery != null) {
            String[] data = callBackQuery.getData().split(":::");
            if (data.length == 2) {
                String fileName = data[0];
                int rating = Integer.parseInt(data[1]);
                add(fileName, rating);
            }
        }
    }

    public double getRating(String fileName) {
        return rating.get(fileName)/rating.size();
    }

    private void add(String fileName, int rate) {
        if (rating.containsKey(fileName)) {
            Integer currentRating = rating.get(fileName);
            currentRating += rate;
            rating.put(fileName, currentRating);
        }
    }

}
