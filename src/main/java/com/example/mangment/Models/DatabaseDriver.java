package com.example.mangment.Models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;

public class DatabaseDriver {
    private Connection connection;
    private static DatabaseDriver databaseDriver;

    public static DatabaseDriver getInstance()
    {
        if(databaseDriver == null)
        {
            databaseDriver = new DatabaseDriver();
        }

        return databaseDriver;
    }

    private  DatabaseDriver()
    {
        try{
            this.connection = DriverManager.getConnection("jdbc:sqlite:demoBank.db");
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public ResultSet getClientData(String pAddress, String password)
    {
        Statement statement;
        ResultSet resultSet = null;
        try{
            statement = this.connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM CLIENTS WHERE PayeeAddress='"+pAddress+"' AND Password = '"+password+"';");
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        return resultSet;
    }

    public ResultSet getAdminData(String username, String password)
    {
        Statement statement;
        ResultSet resultSet = null;
        try{
            statement = this.connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM ADMINS WHERE Username='"+username+"' AND Password = '"+password+"';");
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        return resultSet;
    }

    public void createClient(String fname, String lname, String pAddress, String password, LocalDate date)
    {
        Statement statement;
        try{
            statement = this.connection.createStatement();
            statement.executeUpdate("INSERT INTO "+ " Clients(FirstName, LastName, PayeeAddress, Password, Date) Values ('"+fname+"', '"+lname+"', '"+pAddress+"', '"+password+"', '"+date.toString()+"');");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public void createCheckingAccount(String owner, String number, double tLimit, double balance)
    {
        Statement statement;
        try{
            statement = this.connection.createStatement();
            statement.executeUpdate("INSERT INTO "+ " CheckingAccounts (Owner, AccountNumber, TransactionLimit, Balance) Values ('"+owner+"', '"+number+"', '"+tLimit+"', '"+balance+"');");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public void createSavingsAccount(String owner, String number, double wLimit, double balance)
    {
        Statement statement;
        try{
            statement = this.connection.createStatement();
            statement.executeUpdate("INSERT INTO "+ " SavingsAccounts (Owner, AccountNumber, WithdrawalLimit, Balance) Values ('"+owner+"', '"+number+"', '"+wLimit+"', '"+balance+"');");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }


    public ResultSet getAllClientsData()
    {
        Statement statement;
        ResultSet resultSet = null;
        try{
            statement = this.connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Clients;");
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        return resultSet;
    }

    public ResultSet getCheckingAccountData(String pAddress)
    {
        Statement statement;
        ResultSet resultSet = null;
        try{
            statement = this.connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM CheckingAccounts WHERE Owner='"+pAddress+"';");
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        return resultSet;
    }

    public ResultSet getSavingsAccountData(String pAddress)
    {
        Statement statement;
        ResultSet resultSet = null;
        try{
            statement = this.connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM SavingsAccounts WHERE Owner='"+pAddress+"';");
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        return resultSet;
    }


    public ResultSet searchClient(String pAddress)
    {
        Statement statement;
        ResultSet resultSet = null;
        try{
            statement = this.connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Clients WHERE PayeeAddress='"+pAddress+"';");
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        return resultSet;
    }

    public void deleteClient(String pAddress)
    {
        Statement statement;
        try{
            statement = this.connection.createStatement();
            statement.executeUpdate("DELETE FROM Clients WHERE PayeeAddress='"+pAddress+"';");

            statement = this.connection.createStatement();
            statement.executeUpdate("DELETE FROM SavingsAccounts WHERE Owner='"+pAddress+"';");

            statement = this.connection.createStatement();
            statement.executeUpdate("DELETE FROM CheckingAccounts WHERE Owner='"+pAddress+"';");
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public void depositSavings(String payeeAddress, double amount)
    {
        Statement statement;
        try {
            statement = this.connection.createStatement();
            statement.executeUpdate("Update SavingsAccounts SET Balance =" + amount + " Where Owner ='" + payeeAddress + "';");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public ResultSet getTranscactions(String pAddress, int limit)
    {
        Statement statement;
        ResultSet resultSet = null;
        try{
            statement = this.connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Transactions WHERE Sender='"+pAddress+"' OR Receiver = '"+pAddress+"' ORDER BY Date DESC LIMIT "+limit+";");
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        return resultSet;
    }

    public double getAccountBalance(String payeeAddress, String accountType)
    {
        Statement statement;
        ResultSet resultSet = null;
        double balance = 0;
        String tableName= "SavingsAccounts";
        if(accountType.equals("CheckingAccount")){
            tableName = "CheckingAccounts";
        }
        try{
            statement = this.connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM "+tableName+" WHERE Owner='"+payeeAddress+"';");
            balance = resultSet.getDouble("Balance");
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        return balance;
    }

    public void updateBalance(String payeeAddress, double amount, String operation, String accountType)
    {
        Statement statement;
        ResultSet resultSet;
        String tableName= "SavingsAccounts";
        if(accountType.equals("CheckingAccount")){
            tableName = "CheckingAccounts";
        }
        try {
            statement = this.connection.createStatement();

            resultSet = statement.executeQuery("SELECT * FROM "+tableName+" WHERE Owner='"+payeeAddress+"';");
            double newBalance = 0;
            if(operation.equals("ADD"))
            {
                newBalance  =resultSet.getDouble("Balance") + amount;
                statement.executeUpdate("Update "+tableName+" SET Balance =" + newBalance + " Where Owner ='" + payeeAddress + "';");
            }
            else{
                if(resultSet.getDouble("Balance") > amount) {
                    newBalance  =resultSet.getDouble("Balance") - amount;
                    statement.executeUpdate("Update "+tableName+" SET Balance =" + newBalance + " Where Owner ='" + payeeAddress + "';");
                }
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public void newTransaction(String sender, String receiver, double amount, String message)
    {
        Statement statement;
        try{
            statement = this.connection.createStatement();
            statement.executeUpdate("INSERT INTO "+ " Transactions (Sender, Receiver, Amount, Date, Message) Values ('"+sender+"', '"+receiver+"', '"+amount+"', '"+LocalDate.now().toString()+"', '"+message+"');");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

}
