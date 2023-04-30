package com.example.mangment.Controllers;

import com.example.mangment.Models.Model;
import com.example.mangment.Views.AccountType;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
    public TextField password_fld;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        acc_selector.setItems(FXCollections.observableArrayList(AccountType.CLIENT, AccountType.ADMIN));
        acc_selector.setValue(Model.getInstance().getViewFactory().getLoggedInAccountType());
        acc_selector.valueProperty().addListener(observable -> Model.getInstance().getViewFactory().setLoggedInAccountType(acc_selector.getValue()));
        login_btn.setOnAction(actionEvent -> onLogin());
    }

    private void onLogin(){
        Stage stage=(Stage)error_lbl.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
        if(Model.getInstance().getViewFactory().getLoggedInAccountType() == AccountType.CLIENT)
        {
            Model.getInstance().getViewFactory().showClientWindow();
        }
        else{
            Model.getInstance().getViewFactory().showAdminWindow();
        }
    }
}
