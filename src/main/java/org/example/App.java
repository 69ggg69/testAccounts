package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.errors.LimitAccountException;
import org.example.errors.MakeDebitException;
import org.example.domain.AccountRepository;
import org.example.domain.TransferMoney;


public class App {
    protected static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) throws LimitAccountException, MakeDebitException {


    }
}
