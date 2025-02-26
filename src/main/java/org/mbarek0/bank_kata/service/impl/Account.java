package org.mbarek0.bank_kata.service.impl;

import lombok.AllArgsConstructor;
import org.mbarek0.bank_kata.entity.Transaction;
import org.mbarek0.bank_kata.outil.StatementPrinter;
import org.mbarek0.bank_kata.repository.TransactionRepository;
import org.mbarek0.bank_kata.service.AccountService;

import java.util.List;

@AllArgsConstructor
public class Account implements AccountService {
    private final TransactionRepository transactionRepository;
    private final StatementPrinter statementPrinter;

    @Override
    public void deposit(int amount) {

    }

    @Override
    public void withdraw(int amount) {
    }

    @Override
    public void printStatement() {
    }
}
