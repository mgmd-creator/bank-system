package com.bank.model;

import com.bank.exception.AccountClosedException;
import com.bank.exception.InsufficientFundsException;

import java.util.ArrayList;
import java.util.List;

public class DebitAccount implements Account, WithdrawableAccount {
    private final String id;
    private double balance;
    private boolean active;
    private final List<Operation> operations = new ArrayList<>(); // История операций

    public DebitAccount(String id, double initialBalance) {
        if (initialBalance < 0) {
            throw new IllegalArgumentException("Начальный баланс не может быть отрицательным");
        }
        this.id = id;
        this.balance = initialBalance;
        this.active = true;
    }

    @Override
    public String getId() { return id; }

    @Override
    public double getBalance() { return balance; }

    @Override
    public boolean isActive() { return active; }

    @Override
    public void deposit(double amount) throws AccountClosedException {
        if (!active) throw new AccountClosedException("Счет закрыт");
        if (amount <= 0) throw new IllegalArgumentException("Сумма должна быть больше 0");

        this.balance += amount;
        operations.add(new Operation(amount, Operation.Type.DEPOSIT)); // Записываем операцию
    }

    @Override
    public void withdraw(double amount) throws InsufficientFundsException, AccountClosedException {
        if (!active) throw new AccountClosedException("Счет закрыт");
        if (amount <= 0) throw new IllegalArgumentException("Сумма должна быть больше 0");
        if (amount > balance) {
            throw new InsufficientFundsException("Недостаточно средств. Баланс: " + balance);
        }

        this.balance -= amount;
        operations.add(new Operation(amount, Operation.Type.WITHDRAW)); // Записываем операцию
    }

    @Override
    public void close() throws AccountClosedException {
        if (!active) throw new AccountClosedException("Счет уже закрыт");
        this.active = false;
    }

    public List<Operation> getOperations() {
        return operations;
    }
}