package com.bank.exception;

public class AccountClosedException extends BankException {
    public AccountClosedException(String message) {
        super(message);
    }
}