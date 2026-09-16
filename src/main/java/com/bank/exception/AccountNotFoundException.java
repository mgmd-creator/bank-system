package com.bank.exception;

/**
 * Исключение, выбрасываемое, когда запрашиваемый банковский счет не найден в системе.
 */
public class AccountNotFoundException extends BankException {
    public AccountNotFoundException(String message) {
        super(message);
    }
}