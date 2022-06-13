package com.jdelijser.bibliotheek.controller;

import com.jdelijser.bibliotheek.model.Publisher;
import com.jdelijser.bibliotheek.services.ActivePublisher;
import com.jdelijser.bibliotheek.services.FileService;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PublisherController implements Initializable {
    public ArrayList<Publisher> publishers = new ArrayList<>();

    @FXML
    protected ListView<Publisher> publisherList;

    @FXML
    public Label publisherTitle;

    @FXML
    protected void backToLibrary() throws IOException {
        SceneController.toMain();
    }

    @FXML
    protected void addPublisher() throws IOException {
        SceneController.setScene("add-publisher-view.fxml", "Add book");
    }

    @FXML
    protected void deletePublisher() throws IOException {
        FileService.deleteActivePublisher();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.showPublisherList();
    }

    public void setSelectedPublisher(Publisher publisher) {
        this.publisherTitle.setText(publisher.name);
    }

    public void showPublisherList() {
        this.publishers = FileService.getPublishers();

        int count = 1;
        for (Publisher publisher : this.publishers) {
            if (publisher != null) {
                if (count == 1) {
                    Publisher activePublisher = ActivePublisher.getInstance().getPublisher();

                    if (activePublisher != null) {
                        this.setSelectedPublisher(activePublisher);
                    } else {
                        this.setSelectedPublisher(publisher);
                    }

                    count++;
                }

                this.publisherList.getItems().add(publisher);
            }
        }

        this.publisherList.setCellFactory(new Callback<ListView<Publisher>, ListCell<Publisher>>() {
            @Override
            public ListCell<Publisher> call(ListView<Publisher> listView) {
                ListCell<Publisher> cell = new ListCell<Publisher>() {
                    @Override
                    public void updateItem(Publisher publisher, boolean empty) {
                        super.updateItem(publisher, empty);
                        textProperty().unbind();

                        if (publisher != null) {
                            textProperty().bind(Bindings.format("%s", publisher.name));
                        }
                    }
                };

                cell.setOnMouseClicked(e -> {
                    if (!cell.isEmpty()) {
                        Publisher publisher = cell.getItem();
                        ActivePublisher activePublisher = ActivePublisher.getInstance();
                        activePublisher.setPublisher(publisher);

                        try {
                            SceneController.setScene("publisher-view.fxml", "Publishers");
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }

                        e.consume();
                    }
                });

                return cell;
            }
        });
    }
}