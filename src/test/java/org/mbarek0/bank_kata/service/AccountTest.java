package org.mbarek0.bank_kata.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mbarek0.bank_kata.entity.Transaction;
import org.mbarek0.bank_kata.outil.Clock;
import org.mbarek0.bank_kata.outil.StatementPrinter;
import org.mbarek0.bank_kata.repository.TransactionRepository;
import org.mbarek0.bank_kata.service.impl.Account;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertThrows;


class AccountTest {
    private final Clock clock = mock(Clock.class);
    private TransactionRepository transactionRepository;
    private StatementPrinter statementPrinter;
    private Account account;



    @BeforeEach
    void setUp() {
        transactionRepository = mock(TransactionRepository.class);
        statementPrinter = mock(StatementPrinter.class);
        account = new Account(transactionRepository, statementPrinter);
    }

    @Test
    void deposit_shouldStoreTransaction() {

        // Arrange
        int depositAmount = 100;

        // Act
        account.deposit(depositAmount);

        /*
         Assert that the transactionRepository.addTransaction method
         was called with the depositAmount
         */
        verify(transactionRepository).addTransaction(depositAmount);
    }

    @Test
    void withdraw_shouldStoreTransaction() {
        // Arrange
        int initialDeposit = 100;
        when(transactionRepository.getBalance()).thenReturn(initialDeposit);
        int withdrawAmount = 50;

        // Act
        account.withdraw(withdrawAmount);

        // Assert
        verify(transactionRepository).addTransaction(-withdrawAmount);
    }


    @Test
    void deposit_shouldThrowException_whenAmountIsZero() {

        // Arrange
        int zeroAmount = 0;

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> account.deposit(zeroAmount));

        // Assert
        assertEquals(Account.INVALID_AMOUNT_ERROR, exception.getMessage());
    }

    @Test
    void deposit_shouldThrowException_whenAmountIsNegative() {

        // Arrange
        int negativeAmount = -50;

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> account.deposit(negativeAmount));

        // Assert
        assertEquals(Account.INVALID_AMOUNT_ERROR, exception.getMessage());
    }

    @Test
    void withdraw_shouldThrowException_whenAmountIsNegative() {

        // Arrange
        int negativeAmount = -50;

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> account.withdraw(negativeAmount));

        // Assert
        assertEquals(Account.INVALID_AMOUNT_ERROR, exception.getMessage());
    }


    @Test
    void printStatement_shouldCallStatementPrinter() {

        // Arrange
        LocalDate fixedDate = LocalDate.of(2024, 2, 25);
        when(clock.today()).thenReturn(fixedDate);

        // Arrange
        List<Transaction> transactions = List.of(
                new Transaction(100,clock.today(),100 ),
                new Transaction(-50,clock.today().plusDays(1),50 ));

        when(transactionRepository.getAllTransactions()).thenReturn(transactions);

        // Act
        account.printStatement();

        // Assert
        verify(statementPrinter).print(transactions);
    }

    @Test
    void withdraw_shouldThrowException_whenInsufficientFunds() {
        // Arrange
        int initialDeposit = 100;
        int withdrawAmount = 150;

        when(transactionRepository.getBalance()).thenReturn(initialDeposit);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> account.withdraw(withdrawAmount));

        // Assert
        assertEquals(Account.INSUFFICIENT_FUNDS_ERROR, exception.getMessage());

        // Verify that no transaction was recorded
        verify(transactionRepository, never()).addTransaction(anyInt());
    }

}
