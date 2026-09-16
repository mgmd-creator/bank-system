package com.bank.service;

import com.bank.exception.AccountClosedException;
import com.bank.exception.AccountNotFoundException;
import com.bank.exception.BankException;
import com.bank.model.Account;
import com.bank.model.CreditAccount;
import com.bank.model.DebitAccount;
import com.bank.model.WithdrawableAccount;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BankService {
    private final Map<String, Account> accounts = new HashMap<>();

    public void addAccount(Account account) {
        if (account != null) {
            accounts.put(account.getId(), account);
        }
    }

    public void createDebitAccount(String id, double balance) {
        DebitAccount account = new DebitAccount(id, balance);
        addAccount(account);
    }

    public void createCreditAccount(String id, double balance, double limit) {
        CreditAccount account = new CreditAccount(id, balance, limit);
        addAccount(account);
    }

    public Account findAccount(String id) throws AccountNotFoundException {
        Account account = accounts.get(id);
        if (account == null) {
            throw new AccountNotFoundException("Счет с ID " + id + " не найден");
        }
        return account;
    }

    public void deposit(String id, double amount) throws BankException {
        Account account = findAccount(id);
        account.deposit(amount);
    }

    public void withdraw(String id, double amount) throws BankException, AccountNotFoundException {
        Account account = findAccount(id);
        if (!(account instanceof WithdrawableAccount)) {
            throw new BankException("Этот счет не поддерживает снятие средств");
        }
        ((WithdrawableAccount) account).withdraw(amount);
    }

    public void transfer(String fromId, String toId, double amount) throws BankException {
        Account fromAccount = findAccount(fromId);
        Account toAccount = findAccount(toId);

        if (!fromAccount.isActive()) {
            throw new AccountClosedException("Счет отправителя закрыт: " + fromId);
        }
        if (!toAccount.isActive()) {
            throw new AccountClosedException("Счет получателя закрыт: " + toId);
        }

        if (!(fromAccount instanceof WithdrawableAccount)) {
            throw new BankException("Счет отправителя не поддерживает снятие средств");
        }

        ((WithdrawableAccount) fromAccount).withdraw(amount);
        toAccount.deposit(amount);
    }

    public List<Account> getAllAccounts() {
        return new ArrayList<>(accounts.values());
    }
}