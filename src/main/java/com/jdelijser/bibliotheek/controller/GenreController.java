package com.jdelijser.bibliotheek.controller;

import javafx.fxml.FXML;

import java.io.IOException;

public class GenreController {
    @FXML
    protected void backToLibrary() throws IOException {
        SceneController.toMain();
    }

    @FXML
    protected void addGenre() throws IOException {
        SceneController.setScene("add-genre-view.fxml", "Add book");
    }

    @FXML
    protected void deleteGenre() {

    }
}