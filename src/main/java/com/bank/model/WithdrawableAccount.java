package com.bank.model;

import com.bank.exception.AccountClosedException;
import com.bank.exception.InsufficientFundsException;

/**
 * Интерфейс для счетов, с которых можно снимать денежные средства.
 */
public interface WithdrawableAccount {

    /**
     * Снимает указанную сумму со счета.
     *
     * @param amount сумма для снятия
     * @throws InsufficientFundsException если на счете недостаточно средств
     */
    void withdraw(double amount) throws InsufficientFundsException, AccountClosedException;
}