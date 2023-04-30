module com.example.mangment {
    requires javafx.controls;
    requires javafx.fxml;
    requires de.jensd.fx.glyphs.fontawesome;
    requires java.sql;
    requires org.xerial.sqlitejdbc;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;

    opens com.example.mangment to javafx.fxml;
    exports com.example.mangment;
    exports com.example.mangment.Models;
    exports com.example.mangment.Views;
    exports com.example.mangment.Controllers;
    opens com.example.mangment.Controllers.Client to javafx.fxml;
    opens com.example.mangment.Controllers.Admin to javafx.fxml;
}
