package com.jdelijser.bibliotheek.controller;
import javafx.fxml.FXML;

import java.io.IOException;

public class LibraryController {
    @FXML
    protected void openGenres() throws IOException {
        SceneController.setScene("genre-view.fxml", "Genres");
    }

    @FXML
    protected void openAuthors() throws IOException {
        SceneController.setScene("author-view.fxml", "Authors");
    }

    @FXML
    protected void openPublishers() throws IOException {
        SceneController.setScene("publisher-view.fxml", "Publishers");
    }

    @FXML
    protected void addBook() {

    }

    @FXML
    protected void deleteBook() {

    }
}