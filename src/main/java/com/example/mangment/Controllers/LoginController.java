package com.example.mangment.Controllers;

import com.example.mangment.Controllers.Login.AdminLoginStrategy;
import com.example.mangment.Controllers.Login.ClientLoginStrategy;
import com.example.mangment.Controllers.Login.LoginContext;
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
        LoginContext context=new LoginContext();

        if(Model.getInstance().getViewFactory().getLoggedInAccountType() == AccountType.CLIENT)
        {
            //client
            context.setStrategy(new ClientLoginStrategy());
        }
        else{
            // admin
            context.setStrategy(new AdminLoginStrategy());
        }
        context.performLogin(payee_address_fld,password_fld,stage,error_lbl);
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
