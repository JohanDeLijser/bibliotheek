package com.jdelijser.bibliotheek.controller;

import com.jdelijser.bibliotheek.model.Author;
import com.jdelijser.bibliotheek.services.FileService;
import com.jdelijser.bibliotheek.services.StorageAdapter;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

public class AddAuthorController {
    @FXML
    public TextField authorName;

    @FXML
    protected void addAuthor() throws IOException, SQLException {
        String name = this.authorName.getText();

        if (!name.equals("")) {
            Author author = new Author(name);

            StorageAdapter.addAuthor(author);

            SceneController.setScene("author-view.fxml", "Authors");
        }
    }

    @FXML
    protected void cancelAuthor() throws IOException {
        SceneController.setScene("author-view.fxml", "Authors");
    }
}