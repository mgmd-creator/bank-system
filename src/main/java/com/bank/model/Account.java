package com.bank.model;

import com.bank.exception.AccountClosedException;

public interface Account {
    String getId();
    double getBalance();
    boolean isActive();

    void deposit(double amount) throws AccountClosedException;
    void close() throws AccountClosedException;
}