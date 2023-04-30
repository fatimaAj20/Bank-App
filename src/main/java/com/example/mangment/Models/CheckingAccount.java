package com.example.mangment.Models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class CheckingAccount extends Account{

    private final IntegerProperty transactionLimit;

    public CheckingAccount(String owner, String accNumber, double balance, int transactionLimit) {
        super(owner, accNumber, balance);
        this.transactionLimit  = new SimpleIntegerProperty(this, "Transactions Limit", transactionLimit);
    }

    public IntegerProperty transactionLimitProperty() {
        return transactionLimit;
    }

    @Override
    public  String toString()
    {
        return accountNumberProperty().get();
    }
}
