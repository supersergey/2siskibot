package ua.kiev.supersergey.siski_bot.images_provider;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;
import ua.kiev.supersergey.siski_bot.rest.RestService;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by sergey on 30.11.2016.
 */
@Component
public class ImageLoaderService {
    @Value("${images.url}")
    private String url;
    private List<String> images;
    private static final Random RANDOM_GENERATOR = new Random();

    @PostConstruct
    public void initImages() {
        images = loadImages();
    }

    public String getRandomImage() {
        if (!CollectionUtils.isEmpty(images)) {
            int randomIndex = RANDOM_GENERATOR.nextInt(images.size());
            return images.get(randomIndex);
        } else {
            return "";
        }
    }

    @Scheduled(fixedDelay = 60000)
    private List<String> loadImages() {
        RestTemplate template = new RestTemplate();
        ResponseEntity<String> entity = template.getForEntity(url + "/filelist.php", String.class);
        if (!RestService.isError(entity.getStatusCode())) {
            String files = entity.getBody();
            JsonArray arr = new Gson().fromJson(files, JsonArray.class);
            return IntStream
                    .range(0, arr.size())
                    .mapToObj(i -> arr.get(i).getAsString())
                    .filter(i -> !i.matches("^\\.{1,2}$"))
                    .filter(i -> i.toLowerCase().endsWith("jpg"))
                    .map(i -> url + "/" + i)
                    .collect(Collectors.toList());
        } else {
            return Collections.EMPTY_LIST;
        }
    }


}
