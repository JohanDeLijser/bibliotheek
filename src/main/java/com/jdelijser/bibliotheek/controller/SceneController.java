package com.jdelijser.bibliotheek.controller;

import com.jdelijser.bibliotheek.LibraryApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneController {
    public static Stage mainStage;

    public static void setStage(Stage stage) {
        mainStage = stage;
    }

    public static void toMain() throws IOException {
        SceneController.setScene("library-view.fxml", "Library");
    }

    public static void setScene(String resourceName, String title) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LibraryApplication.class.getResource(resourceName));
        Scene scene = new Scene(fxmlLoader.load(), 800, 400);
        mainStage.setTitle(title);
        mainStage.setScene(scene);
        mainStage.show();
    }
}
