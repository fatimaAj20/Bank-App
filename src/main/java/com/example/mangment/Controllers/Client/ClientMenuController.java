package com.example.mangment.Controllers.Client;

import com.example.mangment.Models.Model;
import com.example.mangment.Views.ClientMenuOptions;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.awt.Desktop;
import java.net.URI;
import java.net.URL;
import java.util.ResourceBundle;

public class ClientMenuController implements Initializable {
    public Button dashboard_btn;
    public Button transaction_btn;
    public Button accounts_btn;
    public Button logout_btn;
    public Button report_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListeners();
    }

    private void  addListeners(){
        dashboard_btn.setOnAction(actionEvent -> onDashboard());
        transaction_btn.setOnAction(actionEvent -> onTransactions());
        accounts_btn.setOnAction(actionEvent -> onAccounts());
        logout_btn.setOnAction(actionEvent -> onLogout());
        report_btn.setOnAction(actionEvent -> onReport());
    }

    private void  onDashboard(){
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set(ClientMenuOptions.DASHBOARD);
    }
    private void onTransactions(){
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set(ClientMenuOptions.TRANSACTIONS);
    }
    private void onAccounts(){
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set(ClientMenuOptions.ACCOUNTS);
    }

    private void onLogout(){
        Stage stage = (Stage) dashboard_btn.getScene().getWindow();

        Model.getInstance().getViewFactory().closeStage(stage);
        Model.getInstance().getViewFactory().showLoginWindow();
        Model.getInstance().setClientLoginSuccessFlag(false);
    }

    private void onReport()
    {
        try {
        Desktop desktop;
        if (Desktop.isDesktopSupported()
                && (desktop = Desktop.getDesktop()).isSupported(Desktop.Action.MAIL)) {
            URI mailto = new URI("mailto:GuiProjectAdmins@lu.edu.lb?subject=Gui%20Project%20Error");
            desktop.mail(mailto);
        }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
