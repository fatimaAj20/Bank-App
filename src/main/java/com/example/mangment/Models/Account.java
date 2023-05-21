package com.example.mangment.Models;

import javafx.beans.property.*;

import java.time.LocalDate;

public abstract class Account {
    private final StringProperty owner;
    private final StringProperty accountNumber;
    private final DoubleProperty balance;
    public Account(String owner, String accNumber, double balance)
    {
        this.owner = new SimpleStringProperty(this, "Owner", owner);
        this.accountNumber = new SimpleStringProperty(this, "Account Number", accNumber);
        this.balance = new SimpleDoubleProperty(this, "Balance", balance);
    }

    public StringProperty ownerProperty() {
        return owner;
    }

    public StringProperty accountNumberProperty() {
        return accountNumber;
    }

    public DoubleProperty balanceProperty() {
        return balance;
    }

    public void setBalance(double balance)
    {
        this.balance.set(balance);
    }
}
