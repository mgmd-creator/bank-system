package com.bank.exception;

/**
 * Исключение, выбрасываемое при попытке снять сумму, превышающую текущий баланс счета.
 */
public class InsufficientFundsException extends BankException {
    public InsufficientFundsException(String message) {
        super(message);
    }
}