package com.bank.model;

import java.time.LocalDateTime;

public class Operation {
    public enum Type {
        DEPOSIT, WITHDRAW
    }

    //точное время транзакций
    private final LocalDateTime dateTime;
    private final double amount;
    private final Type type;
    private final double balanceAfter;

    public Operation(double amount, Type type, double balanceAfter) {
        this.dateTime = LocalDateTime.now();
        this.amount = amount;
        this.type = type;
        this.balanceAfter = balanceAfter;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public double getAmount() {
        return amount;
    }

    public Type getType() {
        return type;
    }

    public double getBalanceAfter() {
        return balanceAfter;
    }

    @Override
    public String toString() {
        return "[" + dateTime.toString().replace('T', ' ') + "] Вид: " + type +
                ", Сумма: " + amount + ", Остаток: " + balanceAfter;
    }
}