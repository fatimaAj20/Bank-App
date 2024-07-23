package com.example.mangment.Models;

import com.example.mangment.Views.AccountType;
import com.example.mangment.Views.ViewFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.time.LocalDate;

public class Model {
    private static Model model;
    private final ViewFactory viewFactory;
    private final DatabaseDriver databaseDriver;
    private Client client;
    private Boolean clientLoginSuccessFlag;
    private Boolean adminLoginSuccessFlag;
    private final ObservableList<Client> clients;


    private final ObservableList<Transaction> latestTransactions;
    private final ObservableList<Transaction> allTransactions;

    private Model(){
        this.viewFactory=new ViewFactory();
        this.databaseDriver = DatabaseDriver.getInstance();
        this.clientLoginSuccessFlag = false;
        this.adminLoginSuccessFlag = false;
        this.client = new Client("","", "", null, null, null);
        this.clients = FXCollections.observableArrayList();
        this.latestTransactions = FXCollections.observableArrayList();
        this.allTransactions = FXCollections.observableArrayList();
    }

    public static synchronized Model getInstance(){
        if(model == null)
        {
            model = new Model();
        }
        return model;
    }

    public ViewFactory getViewFactory() {
        return viewFactory;
    }

    public boolean getClientLoginSuccessFlag()
    {
        return this.clientLoginSuccessFlag;
    }

    public void setClientLoginSuccessFlag(boolean clientLoginSuccessFlag)
    {
        this.clientLoginSuccessFlag = clientLoginSuccessFlag;
    }

    public Client getClient() {
        return client;
    }


    public Boolean getAdminLoginSuccessFlag() {
        return adminLoginSuccessFlag;
    }

    public void setAdminLoginSuccessFlag(Boolean adminLoginSuccessFlag) {
        this.adminLoginSuccessFlag = adminLoginSuccessFlag;
    }

    public void evaluateClientCred(String pAddress, String password)
    {
        CheckingAccount checkingAccount;
        SavingsAccount savingsAccount;
        ResultSet resultSet = databaseDriver.getClientData(pAddress, password);

        try
        {
            if(resultSet.isBeforeFirst())
            {
                this.client.firstNameProperty().set(resultSet.getString("FirstName"));
                this.client.lastNameProperty().set(resultSet.getString("LastName"));
                this.client.payeeAddressProperty().set(resultSet.getString("PayeeAddress"));
                String[] dateParts = resultSet.getString("Date").split("-");
                checkingAccount = getCheckingAccountGivenPAddress(pAddress);
                savingsAccount = getSavingsAccountGivenPAddress(pAddress);
                this.client.dateCreatedProperty().set(LocalDate.of(Integer.parseInt( dateParts[0]), Integer.parseInt(dateParts[1]), Integer.parseInt(dateParts[2])));
                this.client.checkingAccountProperty().set(checkingAccount);
                this.client.savingsAccountProperty().set(savingsAccount);
                this.clientLoginSuccessFlag = true;
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public DatabaseDriver getDatabaseDriver()
    {
        return this.databaseDriver;
    }

    public void evaluateAdminCred(String username, String password)
    {
        ResultSet resultSet = databaseDriver.getAdminData(username, password);
        try
        {
            if(resultSet.isBeforeFirst())
            {
                this.adminLoginSuccessFlag = true;
            }
            else{
                this.adminLoginSuccessFlag = false;
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
    public void setClients() {
        CheckingAccount checkingAccount;
        SavingsAccount savingsAccount;
        ResultSet resultSet =databaseDriver.getAllClientsData();
        try{
            while(resultSet.next())
            {
                String fname = resultSet.getString("FirstName");
                String lname = resultSet.getString("LastName");
                String pAddress = resultSet.getString("PayeeAddress");
                String[] dateParts = resultSet.getString("Date").split("-");
                LocalDate date = LocalDate.of(Integer.parseInt( dateParts[0]), Integer.parseInt(dateParts[1]), Integer.parseInt(dateParts[2]));
                checkingAccount= getCheckingAccountGivenPAddress(pAddress);
                savingsAccount= getSavingsAccountGivenPAddress(pAddress);
                clients.add(new Client(fname, lname, pAddress, checkingAccount, savingsAccount, date));
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public ObservableList<Client> getClients() {
        return clients;
    }

    public CheckingAccount getCheckingAccountGivenPAddress(String pAddress)
    {
        CheckingAccount checkingAccount = null;
        ResultSet resultSet = DatabaseDriver.getInstance().getCheckingAccountData(pAddress);

        try{
            String num=resultSet.getString("AccountNumber");
            int tLimit = (int)resultSet.getInt("TransactionLimit");
            double balance = (int)resultSet.getDouble("Balance");
            checkingAccount = new CheckingAccount(pAddress,num, balance, tLimit );
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        return  checkingAccount;
    }


    public SavingsAccount getSavingsAccountGivenPAddress(String pAddress)
    {
        SavingsAccount savingsAccount = null;
        ResultSet resultSet = DatabaseDriver.getInstance().getSavingsAccountData(pAddress);

        try{
            String num=resultSet.getString("AccountNumber");
            double wLimit = (int)resultSet.getDouble("WithdrawalLimit");
            double balance = (int)resultSet.getDouble("Balance");
            savingsAccount = new SavingsAccount(pAddress,num, balance, wLimit );
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        return  savingsAccount;
    }

    public ObservableList<Client> searchClient(String pAddress)
    {
        ObservableList<Client> searchResults = FXCollections.observableArrayList();
        ResultSet resultSet = databaseDriver.searchClient(pAddress);

        try{
            CheckingAccount checkingAccount = getCheckingAccountGivenPAddress(pAddress);
            SavingsAccount savingsAccount = getSavingsAccountGivenPAddress(pAddress);
            String fname = resultSet.getString("FirstName");
            String lname = resultSet.getString("LastName");
            String[] dateParts = resultSet.getString("Date").split("-");
            LocalDate date = LocalDate.of(Integer.parseInt( dateParts[0]), Integer.parseInt(dateParts[1]), Integer.parseInt(dateParts[2]));
            searchResults.add(new Client(fname,lname, pAddress, savingsAccount, checkingAccount, date));
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        return searchResults;
    }

    private void prepareTransactions(ObservableList<Transaction> transactions, int limit){
        ResultSet resultSet = this.databaseDriver.getTranscactions(this.client.payeeAddressProperty().get(), limit);

        try{
            while(resultSet.next())
            {
                String sender = resultSet.getString("Sender");
                String receiver = resultSet.getString("Receiver");
                double amount  =resultSet.getDouble("Amount");
                String[] dateParts = resultSet.getString("Date").split("-");
                LocalDate date = LocalDate.of(Integer.parseInt( dateParts[0]), Integer.parseInt(dateParts[1]), Integer.parseInt(dateParts[2]));
                String message = resultSet.getString("Message");
                transactions.add(new Transaction(sender, receiver, amount, date, message));
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public void setLatestTransactions()
    {
        prepareTransactions(this.latestTransactions, 4);
    }

    public ObservableList<Transaction> getLatestTransactions()
    {
        return this.latestTransactions;
    }

    public  void setAllTransactions(){
        prepareTransactions(this.allTransactions, -1);
    }

    public ObservableList<Transaction> getAllTransactions()
    {
        return this.allTransactions;
    }


}
