module com.jdelijser.bibliotheek {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.jdelijser.bibliotheek to javafx.fxml;
    exports com.jdelijser.bibliotheek;
    exports com.jdelijser.bibliotheek.controller;
    opens com.jdelijser.bibliotheek.controller to javafx.fxml;
}