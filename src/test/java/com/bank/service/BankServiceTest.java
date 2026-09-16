package com.bank.service;

import com.bank.exception.AccountClosedException;
import com.bank.exception.AccountNotFoundException;
import com.bank.exception.InsufficientFundsException;
import com.bank.model.Account;
import com.bank.model.WithdrawableAccount;

import java.util.HashMap;
import java.util.Map;

public class BankServiceTest {
    private final Map<String, Account> accounts = new HashMap<>();

    public void addAccount(Account account) {
        if (account == null) {
            throw new IllegalArgumentException("Счет не может быть null");
        }
        if (accounts.containsKey(account.getId())) {
            throw new IllegalArgumentException("Счет с таким ID уже существует: " + account.getId());
        }
        accounts.put(account.getId(), account);
    }

    public Account getAccount(String id) throws AccountNotFoundException {
        Account account = accounts.get(id);
        if (account == null) {
            throw new AccountNotFoundException("Счет не найден: " + id);
        }
        return account;
    }

    public void deposit(String accountId, double amount)
            throws AccountNotFoundException, AccountClosedException {
        Account account = getAccount(accountId);
        account.deposit(amount);
    }

    public void withdraw(String accountId, double amount)
            throws AccountNotFoundException, InsufficientFundsException, AccountClosedException {
        Account account = getAccount(accountId);

        if (!(account instanceof WithdrawableAccount)) {
            throw new IllegalArgumentException("С данного типа счета нельзя снимать средства напрямую");
        }

        ((WithdrawableAccount) account).withdraw(amount);
    }

    public void transfer(String fromId, String toId, double amount)
            throws AccountNotFoundException, InsufficientFundsException, AccountClosedException {
        Account fromAccount = getAccount(fromId);
        Account toAccount = getAccount(toId);

        if (!(fromAccount instanceof WithdrawableAccount)) {
            throw new IllegalArgumentException("Счет списания не поддерживает снятие средств");
        }

        ((WithdrawableAccount) fromAccount).withdraw(amount);
        toAccount.deposit(amount);
    }

    public void closeAccount(String accountId)
            throws AccountNotFoundException, AccountClosedException {
        Account account = getAccount(accountId);
        account.close();
    }
}