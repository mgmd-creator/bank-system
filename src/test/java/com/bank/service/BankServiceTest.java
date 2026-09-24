package com.bank.service;

import com.bank.exception.InsufficientFundsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BankServiceTest {

    private BankService bankService;

    @BeforeEach
    void setUp() {
        bankService = new BankService();
    }

    @Test
    void testCreateAndDepositDebitAccount() throws Exception {
        bankService.createDebitAccount("DEB-1", 1000.0);
        bankService.deposit("DEB-1", 500.0);
        assertEquals(1500.0, bankService.findAccount("DEB-1").getBalance(), 0.001);
    }

    @Test
    void testDebitAccountInsufficientFunds() {
        bankService.createDebitAccount("DEB-2", 500.0);

        assertThrows(InsufficientFundsException.class, () -> bankService.withdraw("DEB-2", 1000.0));
    }

    @Test
    void testSuccessfulTransfer() throws Exception {
        bankService.createDebitAccount("DEB-FROM", 2000.0);
        bankService.createDebitAccount("DEB-TO", 500.0);

        bankService.transfer("DEB-FROM", "DEB-TO", 1000.0);

        assertEquals(1000.0, bankService.findAccount("DEB-FROM").getBalance(), 0.001);
        assertEquals(1500.0, bankService.findAccount("DEB-TO").getBalance(), 0.001);
    }
}