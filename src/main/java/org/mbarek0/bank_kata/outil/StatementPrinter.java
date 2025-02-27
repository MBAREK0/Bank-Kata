package org.mbarek0.bank_kata.outil;

import org.mbarek0.bank_kata.entity.Transaction;

import java.util.List;

public class StatementPrinter {
    public void print(List<Transaction> transactions) {
        System.out.println("DATE       || AMOUNT  || BALANCE");
        for (Transaction transaction : transactions) {
            System.out.println(transaction.date() + " ||  " + transaction.amount() + "   ||  " + transaction.balance());
        }
    }
}