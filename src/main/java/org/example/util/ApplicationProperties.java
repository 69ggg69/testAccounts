package org.example.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Properties;


public class ApplicationProperties {
    private static final String rootPath = Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("")).getPath();
    private static final String appConfigPath = rootPath + "application.properties";
    private static final Logger logger = LogManager.getLogger(ApplicationProperties.class);

   public static Properties getAppProps() {
       Properties appProps = new Properties();
       try {
           InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("application.properties");
           appProps.load(inputStream);
           int minDelay = Integer.parseInt(appProps.getProperty("min_delay"));
           int maxDelay = Integer.parseInt(appProps.getProperty("max_delay"));
           int minAmount = Integer.parseInt(appProps.getProperty("min_amount"));
           int maxAmount = Integer.parseInt(appProps.getProperty("max_amount"));

           // Делаем что-то с полученными значениями, например, выводим их в лог
           logger.info("Минимальная задержка: " + minDelay);
           logger.info("Максимальная задержка: " + maxDelay);
           logger.info("Минимальная сумма: " + minAmount);
           logger.info("Максимальная сумма: " + maxAmount);

       } catch (IOException e) {
           logger.error("Не удалось загрузить файл настроек приложения", e);
       }
       return appProps;
   }
   }
//        Properties appProps = new Properties();
//        try {
//            appProps.load(Files.newBufferedReader(Paths.get(appConfigPath)));
//        } catch (IOException e) {
//            logger.error("Не найден файл с настройками приложения");
//        }
//        return appProps;
//    }


