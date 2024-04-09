package org.example.service;

import org.example.entity.Account;
import org.example.errors.BadMoneyException;

public class TransferMoney {
    private final Account sendAccount;
    private final Account takeAccount;
    private final int money;
    public TransferMoney(Account sendAccount, Account takeAccount, int money){
        this.sendAccount=sendAccount;
        this.takeAccount=takeAccount;
        this.money=money;
    }
    public String execute(){
        if (sendAccount.getMoney() >= 0) {
            sendAccount.setMoney(sendAccount.getMoney() - money);
            takeAccount.setMoney(takeAccount.getMoney() + money);
            return "Success!";
        }
        else return "Error!";
    }
}
