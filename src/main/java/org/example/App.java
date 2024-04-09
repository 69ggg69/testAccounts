package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.service.AccountRepository;


public class App {
    protected static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        AccountRepository accountRepository = new AccountRepository();
        logger.info(accountRepository.createAccount());
    }
}
