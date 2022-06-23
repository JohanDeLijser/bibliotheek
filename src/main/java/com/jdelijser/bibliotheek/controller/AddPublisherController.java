package com.jdelijser.bibliotheek.controller;

import com.jdelijser.bibliotheek.model.Publisher;
import com.jdelijser.bibliotheek.services.FileService;
import com.jdelijser.bibliotheek.services.StorageAdapter;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

public class AddPublisherController {
    @FXML
    public TextField publisherName;

    @FXML
    protected void addPublisher() throws IOException, SQLException {
        String name = this.publisherName.getText();

        if (!name.equals("")) {
            Publisher publisher = new Publisher(name);

            StorageAdapter.addPublisher(publisher);

            SceneController.setScene("publisher-view.fxml", "Publishers");
        }
    }

    @FXML
    protected void cancelPublisher() throws IOException {
        SceneController.setScene("publisher-view.fxml", "Publishers");
    }
}