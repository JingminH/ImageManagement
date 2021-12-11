module com.example.imagemanagement {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires javafx.graphics;
    requires java.desktop;
    requires metadata.extractor;

    opens com.example.imagemanagement to javafx.fxml;
    exports com.example.imagemanagement;
}