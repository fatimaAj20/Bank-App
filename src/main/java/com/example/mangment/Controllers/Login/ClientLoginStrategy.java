package com.example.mangment.Controllers.Login;

import com.example.mangment.Models.Model;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ClientLoginStrategy implements LoginStrategy{
    @Override
    public void login(TextField username, TextField password, Stage stage, Label err_lbl) {
        Model.getInstance().evaluateClientCred(username.getText(), password.getText());
        if( Model.getInstance().getClientLoginSuccessFlag())
        {
            Model.getInstance().getViewFactory().closeStage(stage);
            Model.getInstance().getViewFactory().showClientWindow();
        }
        else{
            username.setText("");
            password.setText("");
            err_lbl.setText("No such login credentials exist");
        }
    }
}
