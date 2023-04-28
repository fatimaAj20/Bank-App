package com.example.mangment;

import com.example.mangment.Controller.LoginController;
import com.example.mangment.Model.Model;
import com.example.mangment.Views.ViewFactory;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
//dont need
//public class App extends Application {
//    @Override
//    public void start(Stage stage)throws Exception{
//        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("/Fxml/login.fxml"));
//        Scene scene=new Scene(fxmlLoader.load());
//        stage.show();;
//    }
//    public static void main(String[] args) {
//        launch();
//    }
//}

public class App extends Application {
    @Override
    public void start(Stage stage) {//throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Fxml/login.fxml"));
//        Scene scene = new Scene(fxmlLoader.load());//, 320, 240);
//        stage.setTitle("Bank Management");
//        stage.setScene(scene);
//        stage.show();
        Model.getInstance().getViewFactory().showLoginWindow();
    }

    public static void main(String[] args) {
        launch();
    }
}