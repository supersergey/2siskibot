package ua.kiev.supersergey.siski_bot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import ua.kiev.supersergey.siski_bot.service.images.ImageLoaderService;
import ua.kiev.supersergey.siski_bot.service.images.ImageLoaderServiceImpl;
import ua.kiev.supersergey.siski_bot.service.rating.RatingService;
import ua.kiev.supersergey.siski_bot.service.rating.RatingServiceImpl;
import ua.kiev.supersergey.siski_bot.service.rest.RestService;
import ua.kiev.supersergey.siski_bot.service.rest.RestServiceImpl;

/**
 * Created by sergey on 29.11.2016.
 */
@Configuration
public class AppConfig {
    @Value("${telegram.bot.token}")
    private String token;
    @Value("${images.url}")
    private String imagesUrl;
    @Value("${telegram.api.url}")
    private String telegramUrl;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public ImageLoaderService imageLoaderService() {
        return new ImageLoaderServiceImpl(imagesUrl);
    }

    @Bean
    public RatingService ratingService() {
        return new RatingServiceImpl();
    }

    @Bean
    public RestService restService() {
        return new RestServiceImpl(telegramUrl, token);
    }
}
