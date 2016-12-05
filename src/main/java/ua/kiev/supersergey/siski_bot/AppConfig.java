package ua.kiev.supersergey.siski_bot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
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
@EnableWebMvc
public class AppConfig {
    @Value("${telegram.bot.token}")
    private String token;
    @Value("${images.url}")
    private String imagesUrl;
    @Value("${telegram.api.url}")
    private String telegramUrl;

    @Bean
    public static ThreadPoolTaskExecutor executor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(5);
        executor.setQueueCapacity(25);
        return executor;
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
