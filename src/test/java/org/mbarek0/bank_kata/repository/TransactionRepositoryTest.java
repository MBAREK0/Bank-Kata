package org.mbarek0.bank_kata.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mbarek0.bank_kata.entity.Transaction;
import org.mbarek0.bank_kata.outil.Clock;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class TransactionRepositoryTest {

    private TransactionRepository transactionRepository;
    private Clock clock;

    @BeforeEach
    void setUp() {
        clock = mock(Clock.class);
        transactionRepository = new TransactionRepository(clock);
    }

    @DisplayName("Should add transaction with current date")
    @Test
    void shouldAddTransactionWithCurrentDate() {
        LocalDate fixedDate = LocalDate.of(2024, 2, 25);
        when(clock.today()).thenReturn(fixedDate);


        transactionRepository.addTransaction(1000);
        List<Transaction> transactions = transactionRepository.getAllTransactions();

        // Assert
        assertEquals(1, transactions.size());
        assertEquals(1000, transactions.get(0).amount());
        assertEquals(fixedDate, transactions.get(0).date());
        assertEquals(1000, transactions.get(0).balance());

        verify(clock, times(1)).today();
    }

    @DisplayName("Should store multiple transactions")
    @Test
    void shouldStoreMultipleTransactions() {

        LocalDate fixedDate = LocalDate.of(2024, 2, 25);
        when(clock.today()).thenReturn(fixedDate);


        transactionRepository.addTransaction(1000);
        transactionRepository.addTransaction(2000);
        transactionRepository.addTransaction(-500);
        List<Transaction> transactions = transactionRepository.getAllTransactions();

        // Assert
        assertEquals(3, transactions.size());
        assertEquals(1000, transactions.get(0).amount());
        assertEquals(2000, transactions.get(1).amount());
        assertEquals(-500, transactions.get(2).amount());

        assertEquals(1000, transactions.get(0).balance());
        assertEquals(3000, transactions.get(1).balance());
        assertEquals(2500, transactions.get(2).balance());

        assertEquals(fixedDate, transactions.get(0).date());

        verify(clock, times(3)).today();

    }

    @DisplayName("Should return empty list when no transaction")
    @Test
    void shouldReturnEmptyListWhenNoTransaction() {
        List<Transaction> transactions = transactionRepository.getAllTransactions();

        assertEquals(0, transactions.size());

        verify(clock, never()).today();
    }

    @DisplayName("Should throw exception when amount is zero")
    @Test
    void shouldThrowExceptionWhenAmountIsZero() {
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () ->
                        transactionRepository.addTransaction(0)
                );

        assertEquals(TransactionRepository.INVALID_AMOUNT_ERROR, exception.getMessage());
    }

    @DisplayName("Should throw exception when amount is zero after multiple transactions")
    @Test
    void shouldThrowExceptionWhenAmountIsZeroAfterMultipleTransactions() {
        transactionRepository.addTransaction(1000);
        transactionRepository.addTransaction(2000);

        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () ->
                        transactionRepository.addTransaction(0)
                );

        assertEquals(TransactionRepository.INVALID_AMOUNT_ERROR, exception.getMessage());
    }


}
