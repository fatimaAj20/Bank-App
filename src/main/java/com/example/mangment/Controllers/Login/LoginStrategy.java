package com.example.mangment.Controllers.Login;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public interface LoginStrategy {
    void login(TextField username, TextField password, Stage stage, Label err_lbl);
}
