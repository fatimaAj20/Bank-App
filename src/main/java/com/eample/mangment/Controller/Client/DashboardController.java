package com.example.mangment.Controller.Client;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
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

    }
}
