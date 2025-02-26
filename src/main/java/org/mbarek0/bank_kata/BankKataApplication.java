package org.mbarek0.bank_kata;

import org.mbarek0.bank_kata.outil.Clock;
import org.mbarek0.bank_kata.outil.StatementPrinter;
import org.mbarek0.bank_kata.repository.TransactionRepository;
import org.mbarek0.bank_kata.service.AccountService;
import org.mbarek0.bank_kata.service.impl.Account;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BankKataApplication {

    public static void main(String[] args) {
        Clock clock = new Clock();
        TransactionRepository transactionRepository = new TransactionRepository(clock);
        StatementPrinter statementPrinter = new StatementPrinter();
        AccountService account = new Account(transactionRepository, statementPrinter);

        account.deposit(1000);
        account.deposit(2000);
        account.withdraw(500);
        account.printStatement();

       // SpringApplication.run(BankKataApplication.class, args);
    }

}
