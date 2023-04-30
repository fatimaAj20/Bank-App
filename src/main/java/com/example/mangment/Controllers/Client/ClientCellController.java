package com.example.mangment.Controllers.Client;

import com.example.mangment.Models.Client;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientCellController implements Initializable {
    public Label fname_lbl;
    public Label lname_lbl;
    public Label paddress_lbl;
    public Label ch_acc_lbl;
    public Label sv_acc_lbl;
    public Label date_lbl;
    public Button delete_btn;

    private final Client client;

    public  ClientCellController(Client client)
    {
        this.client = client;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fname_lbl.textProperty().bind(client.firstNameProperty());
        lname_lbl.textProperty().bind(client.lastNameProperty());
        paddress_lbl.textProperty().bind(client.payeeAddressProperty());
        ch_acc_lbl.textProperty().bind(client.checkingAccountProperty().asString());
        sv_acc_lbl.textProperty().bind(client.savingsAccountProperty().asString());
        date_lbl.textProperty().bind(client.dateCreatedProperty().asString());
    }
}
