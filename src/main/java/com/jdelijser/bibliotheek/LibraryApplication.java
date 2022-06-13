package com.jdelijser.bibliotheek;

import com.jdelijser.bibliotheek.controller.SceneController;
import com.jdelijser.bibliotheek.services.DatabaseService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class LibraryApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        SceneController.setStage(stage);
        SceneController.toMain();
    }

    public static void main(String[] args) throws SQLException {
        launch();
    }
}