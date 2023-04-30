package com.example.mangment.Views;

import com.example.mangment.Controllers.Client.ClientCellController;
import com.example.mangment.Controllers.Client.TransactionCellController;
import com.example.mangment.Models.Client;
import com.example.mangment.Models.Transaction;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;

public class ClientCellFactory extends ListCell<Client> {

    @Override
    protected void updateItem(Client client, boolean empty){
        super.updateItem(client, empty);
        if(empty)
        {
            setText(null);
            setGraphic(null);
        }
        else{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Client/ClientCell.fxml"));
            ClientCellController controller = new ClientCellController(client);
            loader.setController(controller);
            setText(null);
            try{
                setGraphic(loader.load());
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
