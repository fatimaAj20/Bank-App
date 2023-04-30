package com.example.mangment.Views;

import com.example.mangment.Controllers.Admin.AdminController;
import com.example.mangment.Controllers.Client.ClientController;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
public class ViewFactory {

    private AccountType LoggedInAccountType;

    //Client View
    private final ObjectProperty<ClientMenuOptions> clientSelectedMenuItem;
    private AnchorPane dashboardView;
    private AnchorPane transactionsView;
    private AnchorPane accountsView;
    private AnchorPane clientsView;
    private AnchorPane depositView;

    private final ObjectProperty<AdminMenuOptions> adminSelectedMenuItem;
    private AnchorPane createClientView;

    public ViewFactory(){
        this.LoggedInAccountType = AccountType.CLIENT;
        this.clientSelectedMenuItem = new SimpleObjectProperty<>();
        this.adminSelectedMenuItem = new SimpleObjectProperty<>();
    }
    public ObjectProperty<ClientMenuOptions> getClientSelectedMenuItem(){
        return clientSelectedMenuItem;
    }

    public AnchorPane getDashboardView() {
        if(dashboardView ==null){
            try {
                dashboardView= new FXMLLoader(getClass().getResource("/Fxml/Client/Dashboard.fxml")).load();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return dashboardView;
    }

    public AnchorPane getClientsView(){
        if(clientsView == null){
            try{
                clientsView = new FXMLLoader(getClass().getResource("/Fxml/Admin/Clients.fxml")).load();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        return clientsView;
    }

    public AnchorPane getTransactionsView() {
        if(transactionsView == null){
            try {
                transactionsView = new FXMLLoader(getClass().getResource("/Fxml/Client/Transactions.fxml")).load();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return transactionsView;
    }

    public AnchorPane getAccountsView() {
        if(accountsView == null){
            try{
                accountsView =new FXMLLoader(getClass().getResource("/Fxml/Client/Accounts.fxml")).load();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return  accountsView;
    }


    public void showClientWindow(){
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/Fxml/Client/Client.fxml"));
        ClientController clientController=new ClientController();
        loader.setController(clientController);
        createStage(loader);

    }

    //Admin Views section

    public ObjectProperty<AdminMenuOptions> getAdminSelectedMenuItem(){
        return adminSelectedMenuItem;
    }
    public AnchorPane getCreateClientView(){
        if(createClientView == null){
            try{
                createClientView = new FXMLLoader(getClass().getResource("/Fxml/Admin/CreateClient.fxml")).load();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return createClientView;
    }

    public AnchorPane getDepositView(){
        if(depositView == null){
            try{
                depositView = new FXMLLoader(getClass().getResource("/Fxml/Admin/Deposit.fxml")).load();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return depositView;
    }

    public void showAdminWindow(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Admin/Admin.fxml"));
        AdminController controller =new AdminController();
        loader.setController(controller);
        createStage(loader);
    }

    public void showLoginWindow(){
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/Fxml/login.fxml"));
        createStage(loader);
    }

    public AccountType getLoggedInAccountType() {
        return LoggedInAccountType;
    }

    public void showMessageWindow(String senderPAddress, String messageText)
    {
        StackPane pane = new StackPane();
        Label sender = new Label(senderPAddress);
        Label message = new Label(messageText);
        VBox vbox = new VBox(5);
        vbox.setAlignment(Pos.CENTER);
        pane.getChildren().add(vbox);

        vbox.getChildren().addAll(sender, message);
        Scene scene = new Scene(pane,300,180);
        Stage stage = new Stage();
        stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/Images/bankIcon.png"))));
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Message");
        stage.setScene(scene);
        stage.show();
    }

    public void setLoggedInAccountType(AccountType loggedInAccountType) {
        LoggedInAccountType = loggedInAccountType;
    }

    private void createStage(FXMLLoader loader) {
        Scene scene= null;
        try{
            scene=new Scene(loader.load());
        }catch (Exception e){
            e.printStackTrace();
        }
        Stage stage=new Stage();
        stage.setScene(scene);
        stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/Images/bankIcon.png"))));
        stage.setResizable(false);
        stage.setTitle("Demo Bank");
        stage.show();
    }
    public void closeStage(Stage stage){
        stage.close();
    }
}
