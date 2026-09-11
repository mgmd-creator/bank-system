package com.bank.model;

import com.bank.exception.AccountClosedException;
import com.bank.exception.InsufficientFundsException;

public interface WithdrawableAccount extends Account {
    void withdraw(double amount) throws InsufficientFundsException, AccountClosedException;
}