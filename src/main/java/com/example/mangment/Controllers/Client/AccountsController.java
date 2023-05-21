package com.example.mangment.Controllers.Client;

import com.example.mangment.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;

public class AccountsController implements Initializable {
    public Label ch_acc_num;
    public Label transaction_limit;
    public Label ch_acc_date;
    public Label ch_acc_bal;
    public Label sv_acc_num;
    public Label withdrawal_limit;
    public Label sv_acc_date;
    public Label sv_acc_bal;
    public TextField amount_to_sv;
    public Button trans_to_sv_btn;
    public TextField amount_to_ch;
    public Button trans_to_cv_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bindData();
        trans_to_sv_btn.setOnAction(actionEvent -> onTransferMoney("CheckingAccount"));
        trans_to_cv_btn.setOnAction(actionEvent -> onTransferMoney("SavingsAccount"));
    }

    private void bindData(){
        ch_acc_bal.textProperty().bind(Model.getInstance().getClient().checkingAccountProperty().get().balanceProperty().asString());
        ch_acc_num.textProperty().bind(Model.getInstance().getClient().checkingAccountProperty().get().accountNumberProperty());
        sv_acc_bal.textProperty().bind(Model.getInstance().getClient().savingsAccountProperty().get().balanceProperty().asString());
        sv_acc_num.textProperty().bind(Model.getInstance().getClient().savingsAccountProperty().get().accountNumberProperty());
    }

    private void onTransferMoney(String accountType)
    {
        Double amount = 0.0;
        String pAddress = Model.getInstance().getClient().payeeAddressProperty().get();
        if(accountType == "CheckingAccount")
        {
            amount = Double.parseDouble(amount_to_sv.getText());
            Model.getInstance().getDatabaseDriver().updateBalance(pAddress, amount, "ADD", accountType);
            Model.getInstance().getClient().checkingAccountProperty().get().setBalance(Model.getInstance().getDatabaseDriver().getAccountBalance(pAddress, accountType));
            amount_to_sv.setText("");
        }
        else{
            amount = Double.parseDouble(amount_to_ch.getText());
            Model.getInstance().getDatabaseDriver().updateBalance(pAddress, amount, "ADD", accountType);
            Model.getInstance().getClient().savingsAccountProperty().get().setBalance(Model.getInstance().getDatabaseDriver().getAccountBalance(pAddress, accountType));
            amount_to_ch.setText("");
        }
    }



}
