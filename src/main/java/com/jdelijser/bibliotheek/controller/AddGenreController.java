package com.jdelijser.bibliotheek.controller;

import com.jdelijser.bibliotheek.model.Genre;
import com.jdelijser.bibliotheek.services.FileService;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AddGenreController {
    @FXML
    public TextField genreName;

    @FXML
    protected void addGenre() throws IOException {
        String name = this.genreName.getText();

        if (!name.equals("")
        ) {
            Genre genre = new Genre(name);

            FileService.writeToFile("genre", genre.name, genre);

            SceneController.setScene("genre-view.fxml", "Genres");
        }
    }

    @FXML
    protected void cancelGenre() throws IOException {
        SceneController.toMain();
    }
}