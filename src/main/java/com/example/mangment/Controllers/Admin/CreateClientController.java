package com.example.mangment.Controllers.Admin;

import com.example.mangment.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.time.LocalDate;
import java.util.Random;
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

    private String payeeAddress;
    private boolean createCheckingAccountFlag = false;

    private boolean createSavingsAccountFlag = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        create_client_btn.setOnAction(actionEvent -> createClient());
        paddress_box.selectedProperty().addListener(((observableValue, aBoolean, t1) -> {
            if(t1)
            {
                payeeAddress = createPayeeAddress();
                onCreatePayeeAddress();
            }
        } ));

        ch_acc_box.selectedProperty().addListener(((observableValue, aBoolean, t1) -> {
            if(t1)
            {
                createCheckingAccountFlag = true;
            }
        } ));


        sv_account_box.selectedProperty().addListener(((observableValue, aBoolean, t1) -> {
            if(t1)
            {
                createSavingsAccountFlag = true;
            }
        } ));
    }

    public void createClient()
    {
        if(createCheckingAccountFlag)
        {
            createAccount("Checking");
        }

        if(createSavingsAccountFlag)
        {
            createAccount("Savings");
        }

        String fname = fname_fld.getText();
        String lname = lname_fld.getText();
        String password = password_fld.getText();
        Model.getInstance().getDatabaseDriver().createClient(fname, lname, payeeAddress, password, LocalDate.now());
        error_lbl.setStyle("-fx-text-fill: blue; -fx-font-size: 1.3em; -fx-font-weight: bold");
        error_lbl.setText("Client Created Successfully");
        emptyFields();
    }

    private void emptyFields()
    {
        fname_fld.setText("");
        lname_fld.setText("");
        password_fld.setText("");
        paddress_box.setSelected(false);
        ch_acc_box.setSelected(false);
        sv_account_box.setSelected(false);
        ch_amount_fld.setText("");
        sv_account_fld.setText("");
    }

    private void onCreatePayeeAddress(){
        if(fname_fld.getText() != null && lname_fld.getText() != null)
        {
            paddress_lbl.setText(payeeAddress);
        }

    }

    private String createPayeeAddress(){
        char fChar = Character.toLowerCase(fname_fld.getText().charAt(0));
        return "@"+fChar+lname_fld.getText()+(new Random()).nextInt(99);
    }

    private void createAccount(String accountType)
    {
        double balance = Double.parseDouble(ch_amount_fld.getText());
        String accountNumber = Integer.toString((new Random()).nextInt(99999999)+10000000);

        if(accountType == "Checking")
        {
            Model.getInstance().getDatabaseDriver().createCheckingAccount(payeeAddress, accountNumber, 10, balance);

        }
        else{
            Model.getInstance().getDatabaseDriver().createSavingsAccount(payeeAddress, accountNumber, 2000, balance);
        }
    }

}


