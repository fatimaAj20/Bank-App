package com.example.mangment.Models;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class SavingsAccount extends Account{

    private final DoubleProperty withdrawalLimit;

    public SavingsAccount(String owner, String accNumber, double balance, double withdrawalLimit) {
        super(owner, accNumber, balance);
        this.withdrawalLimit = new SimpleDoubleProperty(this, "Withdrawal Limit", withdrawalLimit);
    }

    public DoubleProperty withdrawalLimitProperty() {
        return withdrawalLimit;
    }

    @Override
    public  String toString()
    {
        return accountNumberProperty().get();
    }
}
