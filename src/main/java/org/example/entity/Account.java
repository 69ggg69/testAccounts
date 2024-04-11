package org.example.entity;

import org.example.domain.AccountRepository;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Account {
    private String id;
    private int money;
    protected final Lock lock;

    public Account(String id) {
        this.id = id;
        this.money = AccountRepository.getRandomAmount();
        this.lock = new ReentrantLock();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
    public int getCurrentAmount() {
        return this.money;
    }

    public void lockAccount() {
        lock.lock();
    }

    public void unlockAccount() {
        lock.unlock();
    }

    @Override
    public String toString() {
        return "Account{" +
                "id='" + id + '\'' +
                ", money=" + money +
                '}';
    }
}
