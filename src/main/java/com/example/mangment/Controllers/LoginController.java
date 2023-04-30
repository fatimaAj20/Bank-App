package com.example.mangment.Controllers;

import com.example.mangment.Models.Model;
import com.example.mangment.Views.AccountType;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    public Label error_lbl;
    public Button login_btn;
    public Label password_lbl;
    public Label payee_address_lbl;
    public TextField payee_address_fld;
    public ChoiceBox<AccountType> acc_selector;
    public PasswordField password_fld;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        acc_selector.setItems(FXCollections.observableArrayList(AccountType.CLIENT, AccountType.ADMIN));
        acc_selector.setValue(Model.getInstance().getViewFactory().getLoggedInAccountType());
        acc_selector.valueProperty().addListener(observable ->setAcc_selector());
        login_btn.setOnAction(actionEvent -> onLogin());
    }

    private void onLogin(){
        Stage stage=(Stage)error_lbl.getScene().getWindow();

        if(Model.getInstance().getViewFactory().getLoggedInAccountType() == AccountType.CLIENT)
        {
            Model.getInstance().evaluateClientCred(payee_address_fld.getText(), password_fld.getText());
            if( Model.getInstance().getClientLoginSuccessFlag())
            {
                Model.getInstance().getViewFactory().closeStage(stage);
                Model.getInstance().getViewFactory().showClientWindow();
            }
            else{
                payee_address_fld.setText("");
                password_fld.setText("");
                error_lbl.setText("No such login credentials exist");
            }
        }
        else{
            Model.getInstance().evaluateAdminCred(payee_address_fld.getText(), password_fld.getText());
            if( Model.getInstance().getAdminLoginSuccessFlag())
            {
                Model.getInstance().getViewFactory().closeStage(stage);
                Model.getInstance().getViewFactory().showAdminWindow();
            }
            else{
                payee_address_fld.setText("");
                password_fld.setText("");
                error_lbl.setText("No such login credentials exist");
            }
        }
    }

    private void setAcc_selector()
    {
        Model.getInstance().getViewFactory().setLoggedInAccountType(acc_selector.getValue());
        if(acc_selector.getValue() == AccountType.CLIENT)
        {
            payee_address_lbl.setText("Payee Address: ");
        }
        else {
            payee_address_lbl.setText("Username: ");
        }
    }
}
