package com.example.mangment.Controllers.Client;

import com.example.mangment.Models.Model;
import com.example.mangment.Models.Transaction;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class TransactionCellController implements Initializable {
    public FontAwesomeIconView in_icon;
    public FontAwesomeIconView out_icon;
    public Label trans_data_lbl;
    public Label trans_sender_lbl;
    public Label trans_receiver_lbl;
    public Label amount_lbl;
    public Button msg_btn;

    private final Transaction transaction;

    public TransactionCellController(Transaction transaction)
    {
        this.transaction = transaction;
    }

    public void initialize(URL url , ResourceBundle resourceBundle){
        trans_sender_lbl.textProperty().bind(transaction.senderProperty());
        trans_receiver_lbl.textProperty().bind(transaction.receiverProperty());
        amount_lbl.textProperty().bind(transaction.amountProperty().asString());
        trans_data_lbl.textProperty().bind(transaction.dateProperty().asString());
        msg_btn.setOnAction(actionEvent -> Model.getInstance().getViewFactory().showMessageWindow(transaction.senderProperty().get(), transaction.messageProperty().get()));
        transactionIcons();
    }

    private void transactionIcons()
    {
        if(transaction.senderProperty().get().equals(Model.getInstance().getClient().payeeAddressProperty().get()))
        {
            in_icon.setFill(Color.rgb(240,240,240));
            out_icon.setFill(Color.RED);
        }
        else{
            in_icon.setFill(Color.GREEN);
            out_icon.setFill(Color.rgb(240,240,240));
        }
    }

}
