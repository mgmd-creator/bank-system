package com.bank.model;

import java.time.LocalDateTime;

public class Operation {
    public enum Type {
        DEPOSIT, WITHDRAW
    }

    private final LocalDateTime dateTime;
    private final double amount;
    private final Type type;

    public Operation(double amount, Type type) {
        this.dateTime = LocalDateTime.now();
        this.amount = amount;
        this.type = type;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    @Override
    public String toString() {
        return "[" + dateTime.toString().replace('T', ' ') + "] Вид: " + type + ", Сумма: " + amount;
    }
}