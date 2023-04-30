package com.example.mangment.Models;

import javafx.beans.property.*;

import java.time.LocalDate;

public class Client {

    private final StringProperty firstName;
    private final StringProperty lastName;

    private final StringProperty payeeAddress;
    private final DoubleProperty amount;
    private final ObjectProperty<Account> checkingAccount;
    private final ObjectProperty<Account> savingsAccount;
    private final ObjectProperty<LocalDate> dateCreated;

    public Client(String sender, String receiver, Double amount, LocalDate date, String message) {
        this.firstName = new SimpleStringProperty(this, "Sender", sender);
        this.lastName = new SimpleStringProperty(this, "Receiver", receiver);;
        this.amount = new SimpleDoubleProperty(this, "Amount", amount);
        this.date = new SimpleObjectProperty<>(this, "Date", date);
        this.message = new SimpleStringProperty(this, "Message", message);;
    }
}
