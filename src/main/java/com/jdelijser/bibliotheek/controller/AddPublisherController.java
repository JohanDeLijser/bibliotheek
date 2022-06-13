package com.jdelijser.bibliotheek.controller;

import com.jdelijser.bibliotheek.model.Publisher;
import com.jdelijser.bibliotheek.services.FileService;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AddPublisherController {
    @FXML
    public TextField publisherName;

    @FXML
    protected void addPublisher() throws IOException {
        String name = this.publisherName.getText();

        if (!name.equals("")) {
            Publisher publisher = new Publisher(name);

            FileService.writeToFile("publishers", publisher.name, publisher);

            SceneController.setScene("publisher-view.fxml", "Publishers");
        }
    }

    @FXML
    protected void cancelPublisher() throws IOException {
        SceneController.setScene("publisher-view.fxml", "Publishers");
    }
}