package com.jdelijser.bibliotheek.controller;

import com.jdelijser.bibliotheek.model.Genre;
import com.jdelijser.bibliotheek.services.FileService;
import com.jdelijser.bibliotheek.services.StorageAdapter;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

public class AddGenreController {
    @FXML
    public TextField genreName;

    @FXML
    protected void addGenre() throws IOException, SQLException {
        String name = this.genreName.getText();

        if (!name.equals("")) {
            Genre genre = new Genre(name);

            StorageAdapter.addGenre(genre);

            SceneController.setScene("genre-view.fxml", "Genres");
        }
    }

    @FXML
    protected void cancelGenre() throws IOException {
        SceneController.setScene("genre-view.fxml", "Genres");
    }
}