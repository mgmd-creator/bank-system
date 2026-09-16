package com.bank.model;

import com.bank.exception.AccountClosedException;
import com.bank.exception.InsufficientFundsException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreditAccountTest {

    @Test
    void testWithdrawWithinCreditLimit() throws Exception {
        CreditAccount account = new CreditAccount("1", 100.0, 500.0);
        account.withdraw(400.0);
        assertEquals(100.0, account.getBalance(), 0.001);
    }

    @Test
    void testWithdrawExceedsCreditLimit() {
        CreditAccount account = new CreditAccount("1", 100.0, 500.0);
        assertThrows(InsufficientFundsException.class, () -> account.withdraw(700.0));
    }

    @Test
    void testWithdrawClosedCreditAccount() throws Exception {
        CreditAccount account = new CreditAccount("1", 100.0, 500.0);
        account.close();
        assertThrows(AccountClosedException.class, () -> account.withdraw(50.0));
    }

    @Test
    void testInvalidWithdrawAmount() {
        CreditAccount account = new CreditAccount("1", 100.0, 500.0);
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(-50.0));
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(0.0));
    }
}