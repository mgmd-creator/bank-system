package com.bank.exception;

/**
 * Исключение, выбрасываемое при попытке операции с закрытым счетом.
 */
public class AccountClosedException extends BankException {
    public AccountClosedException(String message) {
        super(message);
    }
}