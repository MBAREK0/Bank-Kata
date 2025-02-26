package org.mbarek0.bank_kata.repository;

import lombok.Getter;
import org.mbarek0.bank_kata.entity.Transaction;
import org.mbarek0.bank_kata.outil.Clock;

import java.util.ArrayList;
import java.util.List;

public class TransactionRepository {

    public static final String INVALID_AMOUNT_ERROR = "Amount cannot be zero";

    private final List<Transaction> transactions = new ArrayList<>();
    private final Clock clock;
    private int balance;

    public TransactionRepository(Clock clock) {
        this.clock = clock;
    }

    public void addTransaction(int amount) {
        if (amount == 0) {
            throw new IllegalArgumentException(INVALID_AMOUNT_ERROR);
        }
        balance += amount;
        transactions.add(new Transaction(amount, clock.today(), balance));
    }

    public List<Transaction> getAllTransactions() {
        return transactions;
    }

    // helper method to get balance
    public int getBalance() {
        return transactions.stream().mapToInt(Transaction::amount).sum();
    }
}
