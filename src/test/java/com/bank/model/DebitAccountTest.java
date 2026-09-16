package com.bank.model;

import com.bank.exception.AccountClosedException;
import com.bank.exception.InsufficientFundsException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DebitAccountTest {

    @Test
    void testSuccessfulDeposit() throws Exception {
        DebitAccount account = new DebitAccount("1", 500.0);
        account.deposit(200.0);
        assertEquals(700.0, account.getBalance(), 0.001);
    }

    @Test
    void testDepositInvalidAmount() throws Exception {
        DebitAccount account = new DebitAccount("1", 500.0);
        assertThrows(IllegalArgumentException.class, () -> account.deposit(0.0));
        assertThrows(IllegalArgumentException.class, () -> account.deposit(-100.0));
    }

    @Test
    void testSuccessfulWithdraw() throws Exception {
        DebitAccount account = new DebitAccount("1", 500.0);
        account.withdraw(200.0);
        assertEquals(300.0, account.getBalance(), 0.001);
    }

    @Test
    void testWithdrawInsufficientFunds() {
        DebitAccount account = new DebitAccount("1", 100.0);
        assertThrows(InsufficientFundsException.class, () -> account.withdraw(200.0));
    }

    @Test
    void testWithdrawClosedAccount() throws Exception {
        DebitAccount account = new DebitAccount("1", 500.0);
        account.close();
        assertThrows(AccountClosedException.class, () -> account.withdraw(100.0));
    }

    @Test
    void testCloseAccount() throws Exception {
        DebitAccount account = new DebitAccount("1", 500.0);
        assertTrue(account.isActive());
        account.close();
        assertFalse(account.isActive());
    }
}