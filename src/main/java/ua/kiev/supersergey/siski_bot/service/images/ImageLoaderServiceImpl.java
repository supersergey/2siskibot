package ua.kiev.supersergey.siski_bot.service.images;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import ua.kiev.supersergey.siski_bot.service.rest.RestService;
import ua.kiev.supersergey.siski_bot.service.rest.RestServiceImpl;
import ua.kiev.supersergey.siski_bot.util.RestUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by sergey on 30.11.2016.
 */
public class ImageLoaderServiceImpl implements ImageLoaderService {
    private String url;
    private List<String> images;
    private static final Random RANDOM_GENERATOR = new Random();
    private final RestTemplate restTemplate;
    private static final Logger LOGGER = Logger.getLogger(ImageLoaderServiceImpl.class);

    public ImageLoaderServiceImpl(String url) {
        this.url = url;
        restTemplate = new RestTemplate();
    }

    @Override
    public String getPhotoUrl() {
        return url;
    }

    @Override
    public String getRandomImage() {
        if (CollectionUtils.isEmpty(images)) {
            loadImages();
        };
        int randomIndex = RANDOM_GENERATOR.nextInt(images.size());
        return images.get(randomIndex);
    }

    public int loadImages() {
        String files = getImagesList(url + "/filelist.php");
        JsonArray arr = new Gson().fromJson(files, JsonArray.class);
        List<String> images = new ArrayList<>();
        for (int i = 0; i < arr.size(); i++) {
            String s = arr.get(i).getAsString();
            if (!s.matches("^\\.{1,2}$") && s.toLowerCase().endsWith("jpg")) {
                images.add(s);
            }
        }
        this.images = images;
        return images.size();
    }

    private String getImagesList(String url) {
        try {
            ResponseEntity<String> entity = restTemplate.getForEntity(url + "/filelist.php", String.class);
            if (!RestUtils.isError(entity.getStatusCode())) {
                return entity.getBody();
            }
        } catch (HttpClientErrorException ex) {
            LOGGER.error(ex.getResponseBodyAsString());
            ex.printStackTrace();
            return "";
        }
        return "";
    }
}