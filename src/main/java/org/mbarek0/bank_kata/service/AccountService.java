package org.mbarek0.bank_kata.service;

public interface AccountService {
    void deposit(int amount);
    void withdraw(int amount);
    void printStatement();
}
