package com.example.mangment.Controllers.Client;

import com.example.mangment.Models.Model;
import com.example.mangment.Models.Transaction;
import com.example.mangment.Views.TransactionCellFactory;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.beans.binding.Bindings;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    public FontAwesomeIconView user_name;
    public Label checking_bal;
    public Label checking_acc_num;
    public Label savings_bal;
    public Label savings_acc_num;
    public Label income_lbl;
    public Label expense_lbl;
    public ListView transaction_listview;
    public TextField payee_fld;
    public TextField amount_fld;
    public Button send_money_btn;
    public TextArea message_fld;
    public Label login_date;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bindData();
        initLatestTransactionsList();
        transaction_listview.setItems(Model.getInstance().getLatestTransactions());
        transaction_listview.setCellFactory(e->new TransactionCellFactory());
        send_money_btn.setOnAction(actionEvent -> onSendMoney());
        accountsSummary();
    }

    private void bindData(){
        user_name.textProperty().bind(Bindings.concat("Hi, ").concat(Model.getInstance().getClient().firstNameProperty()));
        login_date.setText("Today " + LocalDate.now());
        checking_bal.textProperty().bind(Model.getInstance().getClient().checkingAccountProperty().get().balanceProperty().asString());
        checking_acc_num.textProperty().bind(Model.getInstance().getClient().checkingAccountProperty().get().accountNumberProperty());
        savings_bal.textProperty().bind(Model.getInstance().getClient().savingsAccountProperty().get().balanceProperty().asString());
        savings_acc_num.textProperty().bind(Model.getInstance().getClient().savingsAccountProperty().get().accountNumberProperty());
    }

    private void initLatestTransactionsList(){
        if (Model.getInstance().getLatestTransactions().isEmpty())
        {
            Model.getInstance().setLatestTransactions();
        }
    }

    private void onSendMoney()
    {
        String receiver = payee_fld.getText();
        double amount = Double.parseDouble(amount_fld.getText());
        String message = message_fld.getText();
        String sender = Model.getInstance().getClient().payeeAddressProperty().get();
        ResultSet resultSet = Model.getInstance().getDatabaseDriver().searchClient(receiver);

        try{
            if(resultSet.isBeforeFirst())
            {
                Model.getInstance().getDatabaseDriver().updateBalance(receiver, amount, "ADD");
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        Model.getInstance().getDatabaseDriver().updateBalance(sender, amount, "SUB");
        Model.getInstance().getClient().savingsAccountProperty().get().setBalance(Model.getInstance().getDatabaseDriver().getSavingsAccountBalance(sender));
        Model.getInstance().getDatabaseDriver().newTransaction(sender, receiver, amount, message);
        payee_fld.setText("");
        amount_fld.setText("");
        message_fld.setText("");
    }

    private void accountsSummary() {
        double income =0;
        double expenses= 0;
        if(Model.getInstance().getAllTransactions().isEmpty())
        {
            Model.getInstance().setAllTransactions();
        }

        for(Transaction transaction: Model.getInstance().getAllTransactions())
        {
            if(transaction.senderProperty().get().equals(Model.getInstance().getClient().payeeAddressProperty().get()))
            {
                expenses = expenses + transaction.amountProperty().get();
            }
            else{
                income = income + transaction.amountProperty().get();
            }
        }

        income_lbl.setText("+ $"+ income);
        expense_lbl.setText("- $" + expenses);
    }
}
