package com.bank.model;

import com.bank.exception.AccountClosedException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DepositAccountTest {

    @Test
    void testDepositSuccessful() throws Exception {
        DepositAccount account = new DepositAccount("1", 1000.0);
        account.deposit(500.0);
        assertEquals(1500.0, account.getBalance(), 0.001);
    }

    @Test
    void testDepositClosedDepositAccount() throws Exception {
        DepositAccount account = new DepositAccount("1", 1000.0);
        account.close();
        assertThrows(AccountClosedException.class, () -> {
            account.deposit(100.0);
        });
    }

    @Test
    void testCloseDepositAccount() throws Exception {
        DepositAccount account = new DepositAccount("1", 1000.0);
        assertTrue(account.isActive());
        account.close();
        assertFalse(account.isActive());
    }
}