module com.jdelijser.bibliotheek {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.jdelijser.bibliotheek to javafx.fxml;
    exports com.jdelijser.bibliotheek;
}