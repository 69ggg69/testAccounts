package org.example.domain;

import org.example.entity.Account;
import org.example.errors.LimitAccountException;
import org.example.errors.MakeDebitException;

import java.util.logging.Logger;

public class TransferMoney {
    private final int money;
    private final Account sendAccount;
    private final Account takeAccount;
    private final Logger logger = Logger.getLogger(TransferMoney.class.getName());

    public TransferMoney(Account sendAccount, Account takeAccount, int money) {
        this.sendAccount = sendAccount;
        this.takeAccount = takeAccount;
        this.money = money;
    }

    public void transfer(int money) throws LimitAccountException, MakeDebitException {
        if (sendAccount.getMoney() < 0) {
            logger.info("Недостаточно средств на счете " + sendAccount.getId() + ". Отмена операции.");
            throw new LimitAccountException("Недостаточно средств на счете " + sendAccount.getId());
        }
        sendAccount.setMoney(sendAccount.getMoney() - money);
        takeAccount.setMoney(takeAccount.getMoney() + money);

}

    public void makeEnrolment(int money) throws InterruptedException {
        sendAccount.setMoney(takeAccount.getMoney() + money);
    }

    @Override
    public String toString() {
        return "TransferMoney{" +
                "sendAccount=" + sendAccount +
                ", takeAccount=" + takeAccount +
                ", money=" + money +
                '}';
    }
}
