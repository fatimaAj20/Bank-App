package com.example.mangment.Controller;

import com.example.mangment.Model.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    public Label error_lbl;
    public Button login_btn;
    public TextField passwoed_fld;
    public Label password_lbl;
    public Label payee_address_lbl;
    public TextField payee_address_fld;
    public ChoiceBox acc_selector;
    public TextField password_fld;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //when someone click on login it will view the Client window(fromViewFactory)
//        login_btn.setOnAction(event -> Model.getInstance().getViewFactory().showClientWindow());
        login_btn.setOnAction(actionEvent -> Model.getInstance().getViewFactory().showClientWindow());

    }
}
