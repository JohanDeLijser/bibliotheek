package com.jdelijser.bibliotheek.controller;

import com.jdelijser.bibliotheek.model.Author;
import com.jdelijser.bibliotheek.services.FileService;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AddAuthorController {
    @FXML
    public TextField authorName;

    @FXML
    protected void addAuthor() throws IOException {
        String name = this.authorName.getText();

        if (!name.equals("")) {
            Author author = new Author(name);

            FileService.writeToFile("authors", author.name, author);

            SceneController.setScene("author-view.fxml", "Authors");
        }
    }

    @FXML
    protected void cancelAuthor() throws IOException {
        SceneController.setScene("author-view.fxml", "Authors");
    }
}