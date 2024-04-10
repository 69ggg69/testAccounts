package org.example.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.domain.AccountRepository;
import org.example.entity.Account;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TransferService {
    private final AccountService service;
    private final ExecutorService threadPool;
    private final List<Account> accountList;
    private final Logger logger = LogManager.getLogger(TransferService.class);

    public TransferService(AccountService service, ExecutorService threadPool, List<Account> accountList) {
        service = new AccountService();
        threadPool = Executors.newFixedThreadPool(Integer.parseInt(totalThreads));
        accountList = service.generateAccountList(Integer.parseInt(totalAccounts));
    }
}
