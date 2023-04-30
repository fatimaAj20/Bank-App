package com.example.mangment.Models;

import javafx.beans.property.*;

import java.time.LocalDate;

public class Client {
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty payeeAddress;
    private final ObjectProperty<Account> checkingAccount;
    private final ObjectProperty<Account> savingsAccount;
    private final ObjectProperty<LocalDate> dateCreated;

    public Client(String firstName, String lastName, String pAddress, Account svAccount, Account chAccount, LocalDate date) {
        this.firstName = new SimpleStringProperty(this, "FirstName", firstName);
        this.lastName = new SimpleStringProperty(this, "LastName", lastName);
        this.payeeAddress = new SimpleStringProperty(this, "Payee Address", pAddress);
        this.checkingAccount = new SimpleObjectProperty<>(this, "Checking Account", chAccount);
        this.savingsAccount = new SimpleObjectProperty<>(this, "Savings Account", svAccount);
        this.dateCreated = new SimpleObjectProperty<>(this, "DateCreated", date);
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public StringProperty payeeAddressProperty() {
        return payeeAddress;
    }

    public ObjectProperty<Account> checkingAccountProperty() {
        return checkingAccount;
    }

    public ObjectProperty<Account> savingsAccountProperty() {
        return savingsAccount;
    }

    public ObjectProperty<LocalDate> dateCreatedProperty() {
        return dateCreated;
    }
}
