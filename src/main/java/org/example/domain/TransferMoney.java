package org.example.domain;

import org.example.entity.Account;
import org.example.errors.LimitAccountException;
import org.example.errors.MakeDebitException;

import java.util.logging.Logger;

public class TransferMoney extends Account{
    private int money;
    private Account sendAccount;
    private Account takeAccount;
    private final Logger logger = Logger.getLogger(TransferMoney.class.getName());

    public TransferMoney(String id) {
        super(id);
    }

    public void execute(int money) throws LimitAccountException, MakeDebitException {
        sendAccount.setMoney(sendAccount.getMoney() - money);
        takeAccount.setMoney(takeAccount.getMoney() + money);
        if (sendAccount.getMoney() < 0) {
            logger.info("Недостаточно средств на счете " + sendAccount.getId());
            throw new LimitAccountException("Недостаточно средств на счете " + sendAccount.getId());
        } else {
            try {
                sendAccount.setMoney(sendAccount.getMoney());
            } catch (Exception e) {
                throw new MakeDebitException("Внутренняя ошибка списания средств");
            }
        }
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
