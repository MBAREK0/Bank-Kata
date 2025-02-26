package org.mbarek0.bank_kata.service.impl;

import lombok.AllArgsConstructor;
import org.mbarek0.bank_kata.entity.Transaction;
import org.mbarek0.bank_kata.outil.StatementPrinter;
import org.mbarek0.bank_kata.repository.TransactionRepository;
import org.mbarek0.bank_kata.service.AccountService;

import java.util.List;

@AllArgsConstructor
public class Account implements AccountService {

    public static final String INVALID_AMOUNT_ERROR = "Amount cannot be zero or negative";
    public static final String INSUFFICIENT_FUNDS_ERROR = "Insufficient funds";

    private final TransactionRepository transactionRepository;
    private final StatementPrinter statementPrinter;

    @Override
    public void deposit(int amount) {
        validateAmount(amount);
        transactionRepository.addTransaction(amount);

    }

    @Override
    public void withdraw(int amount) {
        validateAmount(amount);
        validateBalance(amount);
        transactionRepository.addTransaction(-amount);
    }

    @Override
    public void printStatement() {
        List<Transaction> transactions = getAllTransactions();
        statementPrinter.print(transactions);
    }


    // helper method to get all transactions
    public List<Transaction> getAllTransactions() {
        return transactionRepository.getAllTransactions();
    }

    // helper method to validate amount
    public void validateAmount(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException(INVALID_AMOUNT_ERROR);
        }
    }

    // helper method to validate balance
    public void validateBalance(int amount) {
        if (amount > transactionRepository.getBalance()) {
            throw new IllegalArgumentException(INSUFFICIENT_FUNDS_ERROR);
        }
    }
}
