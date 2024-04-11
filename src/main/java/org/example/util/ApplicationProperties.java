package org.example.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class ApplicationProperties {
    private static final Logger logger = LogManager.getLogger(ApplicationProperties.class);

    public static Properties getAppProps() {
        Properties appProps = new Properties();
        try {
            InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("application.properties");
            appProps.load(inputStream);

        } catch (IOException e) {
            logger.error("Не удалось загрузить файл настроек приложения", e);
        }
        return appProps;
    }
}



