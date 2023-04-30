package com.example.mangment.Controllers.Client;

import com.example.mangment.Models.Transaction;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class TransactionCellController implements Initializable {
    public FontAwesomeIconView in_icon;
    public FontAwesomeIconView out_icon;
    public Label trans_data_lbl;
    public Label trans_sender_lbl;
    public Label trans_receiver_lbl;
    public Label amount_lbl;

    private final Transaction transaction;

    public TransactionCellController(Transaction transaction)
    {
        this.transaction = transaction;
    }

    public void initialize(URL url , ResourceBundle resourceBundle){

    }
}
