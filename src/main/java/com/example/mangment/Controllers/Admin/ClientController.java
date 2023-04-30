package com.example.mangment.Controllers.Admin;

import com.example.mangment.Models.Client;
import com.example.mangment.Models.Model;
import com.example.mangment.Views.ClientCellFactory;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import java.net.URL;
import java.util.ResourceBundle;

public class ClientController implements Initializable {
    public ListView<Client> clients_listview;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initClientData();
        clients_listview.setItems(Model.getInstance().getClients());
        clients_listview.setCellFactory(e->new ClientCellFactory());
    }

    private void initClientData(){
        if(Model.getInstance().getClients().isEmpty())
        {
            Model.getInstance().setClients();
        }
    }
}
