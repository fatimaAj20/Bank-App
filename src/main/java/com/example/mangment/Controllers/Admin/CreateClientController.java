package com.example.mangment.Controllers.Admin;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateClientController implements Initializable {
    public TextField fname_fld;
    public TextField lname_fld;
    public TextField password_fld;
    public CheckBox paddress_box;
    public Text paddress_lbl;
    public CheckBox ch_acc_box;
    public TextField ch_amount_fld;
    public CheckBox sv_account_box;
    public TextField sv_account_fld;
    public Button create_client_btn;
    public Text error_lbl;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
