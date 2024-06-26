package org.example;

import org.example.service.TransferService;
import org.example.util.ApplicationProperties;

import java.util.Properties;


public class App {
    public static void main(String[] args) {
        Properties appProps = ApplicationProperties.getAppProps();
        TransferService transferService = new TransferService(appProps.getProperty("accounts"), appProps.getProperty("threads"));
        transferService.start(appProps.getProperty("transactions"));

    }
}
