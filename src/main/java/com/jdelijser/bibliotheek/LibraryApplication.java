package com.jdelijser.bibliotheek;

import com.jdelijser.bibliotheek.controller.SceneController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LibraryApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        SceneController.setStage(stage);
        SceneController.toMain();
    }

    public static void main(String[] args) {
        launch();
    }
}