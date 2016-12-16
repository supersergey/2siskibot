package ua.kiev.supersergey.siski_bot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.WebApplicationInitializer;
import ua.kiev.supersergey.siski_bot.service.storage.StorageService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

public class MyWebAppInitializer implements WebApplicationInitializer {
 @Autowired
 private StorageService storageService;

 @Autowired
 private StorageService storageService;

 @Override
 public void onStartup(ServletContext container) throws ServletException {
  CronManager cron = new CronManagerImpl();
  cron.loadCronJobs();
 }
}