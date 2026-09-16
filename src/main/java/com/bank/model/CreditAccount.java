package com.bank.model;

import com.bank.exception.AccountClosedException;
import com.bank.exception.InsufficientFundsException;

/**
 * Класс, представляющий кредитный банковский счет.
 */
public class CreditAccount implements Account, WithdrawableAccount {
    private final String id;
    private double balance;
    private final double creditLimit;
    private boolean active;

    public CreditAccount(String id, double initialBalance, double creditLimit) {
        if (creditLimit < 0) {
            throw new IllegalArgumentException("Кредитный лимит не может быть отрицательным");
        }
        if (initialBalance < -creditLimit) {
            throw new IllegalArgumentException("Начальный баланс не может превышать кредитный лимит");
        }
        this.id = id;
        this.balance = initialBalance;
        this.creditLimit = creditLimit;
        this.active = true;
    }

    @Override
    public void withdraw(double amount) throws InsufficientFundsException, AccountClosedException {
        if (!active) {
            throw new AccountClosedException("Нельзя снять деньги с закрытого счета: " + id);
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Сумма снятия должна быть больше 0");
        }
        if (balance - amount < -creditLimit) {
            throw new InsufficientFundsException("Превышен кредитный лимит. Доступно к снятию: " + (balance + creditLimit));
        }
        this.balance -= amount;
    }

    public String getId() { return id; }

    @Override
    public double getBalance() {
        return balance; }

    public double getCreditLimit() {
        return creditLimit; }

    @Override
    public boolean isActive() {
        return active; }

    @Override
    public void deposit(double amount) throws AccountClosedException {
        if (!active) {
            throw new AccountClosedException("Нельзя пополнить закрытый счет: " + id);
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Сумма пополнения должна быть больше 0");
        }
        this.balance += amount;
    }

    @Override
    public void close() throws AccountClosedException {
        if (!active) {
            throw new AccountClosedException("Счет уже закрыт: " + id);
        }
        if (balance < 0) {
            throw new IllegalStateException("Нельзя закрыть счет с задолженностью: " + balance);
        }
        this.active = false;
    }
}