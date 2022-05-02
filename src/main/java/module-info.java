module com.example.synthfx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;
    requires java.sql;
    requires ibatis.core;

    opens com.example.synthfx to javafx.fxml;
    exports com.example.synthfx;
    exports ui.gui;
}