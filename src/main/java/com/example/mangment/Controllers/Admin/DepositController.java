package com.example.mangment.Controllers.Admin;

import com.example.mangment.Models.Client;
import com.example.mangment.Models.Model;
import com.example.mangment.Views.ClientCellFactory;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class DepositController implements Initializable {

    public TextField paddress_fld;
    public Button search_btn;
    public ListView<Client> result_listview;
    public TextField amount_fld;
    public Button deposit_btn;

    private Client client;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        search_btn.setOnAction(actionEvent -> OnClientSearch());
        deposit_btn.setOnAction(actionEvent -> onDeposit());
    }

    private void OnClientSearch(){
        ObservableList<Client> searchResults = Model.getInstance().searchClient(paddress_fld.getText());
        result_listview.setItems(searchResults);
        result_listview.setCellFactory(e->new ClientCellFactory());
        client=searchResults.get(0);

    }

    private void onDeposit()
    {
        double amount = Double.parseDouble(amount_fld.getText());
        double newBalance = amount + client.savingsAccountProperty().get().balanceProperty().get();

        if(amount_fld.getText()!=null)
        {
            Model.getInstance().getDatabaseDriver().depositSavings(client.payeeAddressProperty().get(), newBalance);
        }

        emptyFields();
    }

    private void emptyFields()
    {
        paddress_fld.setText("");
        amount_fld.setText("");
    }
}
