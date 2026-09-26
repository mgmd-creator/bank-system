package com.bank.model;

import com.bank.exception.AccountClosedException;
import com.bank.exception.InsufficientFundsException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DepositAccountTest {

    @Test
    void testDepositSuccessful() throws Exception {
        DepositAccount account = new DepositAccount("1", 1000.0, LocalDate.now().plusYears(1));
        account.deposit(500.0);
        assertEquals(1500.0, account.getBalance(), 0.001);
    }

    @Test
    void testWithdrawBeforeEndDateThrowsException() {
        LocalDate futureDate = LocalDate.now().plusMonths(6);
        DepositAccount account = new DepositAccount("1", 1000.0, futureDate);

        assertThrows(IllegalStateException.class, () -> {
            account.withdraw(100.0);
        });
    }

    @Test
    void testDepositClosedDepositAccount() throws Exception {
        DepositAccount account = new DepositAccount("1", 1000.0, LocalDate.now().plusMonths(1));
        account.close();
        assertThrows(AccountClosedException.class, () -> {
            account.deposit(100.0);
        });
    }

    @Test
    void testCloseDepositAccount() throws Exception {
        DepositAccount account = new DepositAccount("1", 1000.0, LocalDate.now().plusMonths(1));
        assertTrue(account.isActive());
        account.close();
        assertFalse(account.isActive());
    }
}