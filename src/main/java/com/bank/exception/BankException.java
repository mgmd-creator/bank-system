package com.bank.exception;

/**
 * Базовое проверяемое исключение для всех ошибок банковской системы.
 */
public class BankException extends Exception {
    public BankException(String message) {
        super(message);
    }
}