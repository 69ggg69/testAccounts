package org.example.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
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
            appProps.load(Files.newInputStream(Paths.get(appConfigPath)));
        } catch (IOException e) {
            logger.error("Не найден файл с настройками приложения");
        }
        return appProps;
    }


}

