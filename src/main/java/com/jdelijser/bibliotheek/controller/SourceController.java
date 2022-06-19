package com.jdelijser.bibliotheek.controller;

import com.jdelijser.bibliotheek.services.StorageAdapter;
import com.jdelijser.bibliotheek.storage.ActiveSource;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SourceController implements Initializable {
    @FXML
    public ChoiceBox<String> source;

    @FXML
    public Label sourceName;

    @FXML
    protected void saveSource() throws IOException {
        ActiveSource.getInstance().setSource(this.source.getValue());
        SceneController.setScene("source-view.fxml", "Change source");
    }

    @FXML
    protected void backToLibrary() throws IOException {
        SceneController.toMain();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<String> choices = StorageAdapter.getStorageTypes();
        ObservableList<String> choiceNames = FXCollections.observableArrayList();
        choiceNames.addAll(choices);

        this.source.setItems(choiceNames);

        String activeSource = ActiveSource.getInstance().getSource();

        if (activeSource != null) {
            this.sourceName.setText(activeSource);
        } else {
            this.sourceName.setText(choiceNames.get(0));
        }
    }

}