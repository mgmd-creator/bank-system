package com.bank.model;

import com.bank.exception.AccountClosedException;
import com.bank.exception.InsufficientFundsException;

public class DebitAccount implements WithdrawableAccount {
    private final String id;
    private double balance;
    private boolean active;

    public DebitAccount(String id, double initialBalance) {
        if (initialBalance < 0) {
            throw new IllegalArgumentException("Начальный баланс не может быть отрицательным");
        }
            this.id = id;
            this.balance = initialBalance;
            this.active = true;
        }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public boolean isActive() {
        return active;
    }

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
    public void withdraw(double amount) throws InsufficientFundsException, AccountClosedException {
        if (!active) {
            throw new AccountClosedException("Нельзя снять деньги с закрытого счета: " + id);
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Сумма снятия должна быть больше 0");
        }
        if (amount > balance) {
            throw new InsufficientFundsException("Недостаточно средств. Запрошено: " + amount + ", Доступно: " + balance);
        }
        this.balance -= amount;
    }

    @Override
    public void close() throws AccountClosedException {
        if (!active) {
            throw new AccountClosedException("Счет уже закрыт: " + id);
        }
        this.active = false;
    }
}
