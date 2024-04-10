package org.example.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.domain.AccountRepository;
import org.example.entity.Account;
import org.example.errors.BadMoneyException;
import org.example.errors.LimitAccountException;
import org.example.errors.MakeDebitException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AccountService {
    private final Logger logger = LogManager.getLogger(AccountService.class);
    private final Comparator<Account> comparator = Comparator.comparing(Account::getId);

    public void makeTransactionalFromTo(Account sendAccount, Account takeAccount, int money) {
        logger.info("Начата транзакция перевода средств с аккаунта " + sendAccount.getId() + " на аккаунт " + takeAccount.getId() + " сумма транзакции - " + money + ". В потоке - " + Thread.currentThread().getName());
        try {
            if (money == 0 && Integer.signum(money) < 0) throw new BadMoneyException("Неверная сумма перевода");
            makeDebit(sendAccount, takeAccount, money);
            logger.info("Завершена транзакция перевода средств с аккаунта " + sendAccount.getId() + " на аккаунт " + takeAccount.getId() + " сумма транзакции  - " + money + ". В потоке - " + Thread.currentThread().getName());
        } catch (LimitAccountException e) {
            logger.warn("Транзакция перевода средств с аккаунта " + sendAccount.getId() + " на аккаунт " + takeAccount.getId() + " сумма транзакции  - " + money + ". Отменена. Не достаточно средств на счете отправителя");
        } catch (BadMoneyException e) {
            logger.warn("Транзакция перевода средств с аккаунта " + sendAccount.getId() + " на аккаунт " + takeAccount.getId() + " сумма транзакции  - " + money + ". Отменена. Некорректная сумма перевода");
        }
    }

    private void makeDebit(Account sendAccount, Account takeAccount, int money) throws LimitAccountException {
        logger.info("Начат перевод средств с аккаунта " + sendAccount.getId() + " на аккаунт " + takeAccount.getId() + " сумма транзакции  - " + money);
        List<Account> accounts = new ArrayList<>();
        accounts.add(takeAccount);
        accounts.add(sendAccount);
        accounts.sort(comparator);
        try {
            accounts.get(0).lockAccount();
            accounts.get(1).lockAccount();
            sendAccount.makeDebit(money);
            takeAccount.makeEnrolment(money);
        } catch (MakeDebitException e) {
            try {
                sendAccount.makeEnrolment(money);
            } catch (InterruptedException ex) {
                logger.error("Транзакция перевода средств с аккаунта " + sendAccount.getId() + " на аккаунт " + takeAccount.getId() + " сумма транзакции  - " + money + ". Отменена." +
                        " Фатальная ошибка перевода. Средства в объеме - " + money + ". Переведены на счет - " + sendAccount.getId());
            }
        } catch (InterruptedException e) {
            logger.error("Транзакция перевода средств с аккаунта " + sendAccount.getId() + " на аккаунт " + takeAccount.getId() + " сумма транзакции  - " + money + ". Отменена." +
                    " Фатальная ошибка перевода. Средства в объеме - " + money + ". Переведены на счет - " + sendAccount.getId());
        } finally {
            accounts.get(0).unlockAccount();
            accounts.get(1).unlockAccount();
        }
        logger.info("Завершен перевод средств с аккаунта " + sendAccount.getId() + " на аккаунт " + takeAccount.getId() + " итоговый счет аккаунта отправителя - " + sendAccount.getCurrentAmount() + " Итоговый счет аккаунта получателя - " + takeAccount.getCurrentAmount());
    }

    public List<Account> generateAccountList(int count) {
        List<Account> bach = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            bach.add(new Account(AccountRepository.randomId()));
        }
        logger.info("Сгенерировано всего счетов: " + count);
        return bach;
    }

    public int countTotalAmount(List<Account> accounts) {
        return accounts.stream().mapToInt(Account::getCurrentAmount).sum();
    }

    public void makeShuffleTransactionsWithRandomAmount(List<Account> accounts, int[] pair) {
        makeTransactionalFromTo(accounts.get(pair[0]), accounts.get(pair[1]), AccountRepository.getRandomAmount());
    }

}


