package com.bank.model;

import com.bank.exception.AccountClosedException;
import com.bank.exception.InsufficientFundsException;

import java.util.ArrayList;
import java.util.List;

public class CreditAccount implements Account, WithdrawableAccount {
    private final String id;
    private double balance;
    private final double creditLimit;
    private boolean active;
    private final List<Operation> operations = new ArrayList<>(); // История операций

    public CreditAccount(String id, double initialBalance, double creditLimit) {
        if (initialBalance < 0 || creditLimit < 0) {
            throw new IllegalArgumentException("Баланс и лимит не могут быть отрицательными");
        }
        this.id = id;
        this.balance = initialBalance;
        this.creditLimit = creditLimit;
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
        operations.add(new Operation(amount, Operation.Type.DEPOSIT));
    }

    @Override
    public void withdraw(double amount) throws InsufficientFundsException, AccountClosedException {
        if (!active) throw new AccountClosedException("Счет закрыт");
        if (amount <= 0) throw new IllegalArgumentException("Сумма должна быть больше 0");

        if (balance + creditLimit < amount) {
            throw new InsufficientFundsException("Превышен кредитный лимит!");
        }

        this.balance -= amount;
        operations.add(new Operation(amount, Operation.Type.WITHDRAW));
    }

    @Override
    public void close() throws AccountClosedException {
        if (!active) throw new AccountClosedException("Счет уже закрыт");
        if (balance < 0) {
            throw new IllegalStateException("Нельзя закрыть кредитный счет с задолженностью!");
        }
        this.active = false;
    }

    public List<Operation> getOperations() {
        return operations;
    }
}