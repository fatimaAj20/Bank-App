package com.example.mangment.Controllers.Login;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginContext {
    private LoginStrategy strategy;

    public void setStrategy(LoginStrategy strategy) {
        this.strategy = strategy;
    }

    public void performLogin (TextField username, TextField password, Stage stage, Label err_lbl){
        strategy.login(username, password, stage, err_lbl);
    }
}
