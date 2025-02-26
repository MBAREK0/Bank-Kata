package org.mbarek0.bank_kata.entity;

import java.time.LocalDate;

public record Transaction(int amount, LocalDate date, int balance){
}