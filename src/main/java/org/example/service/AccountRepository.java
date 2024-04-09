package org.example.service;

import org.example.entity.Account;

import java.util.UUID;

public class AccountRepository {
    public Account createAccount() {
        return new Account(UUID.randomUUID().toString(), 10000);
    }
}
