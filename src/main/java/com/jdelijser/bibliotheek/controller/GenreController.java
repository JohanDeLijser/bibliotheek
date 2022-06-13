package com.jdelijser.bibliotheek.controller;

import com.jdelijser.bibliotheek.model.Genre;
import com.jdelijser.bibliotheek.services.ActiveGenre;
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

public class GenreController implements Initializable {
    public ArrayList<Genre> genres = new ArrayList<>();

    @FXML
    protected ListView<Genre> genreList;

    @FXML
    public Label genreTitle;

    @FXML
    protected void backToLibrary() throws IOException {
        SceneController.toMain();
    }

    @FXML
    protected void addGenre() throws IOException {
        SceneController.setScene("add-genre-view.fxml", "Add book");
    }

    @FXML
    protected void deleteGenre() throws IOException {
        FileService.deleteActiveGenre();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.showGenreList();
    }

    public void setSelectedGenre(Genre genre) {
        this.genreTitle.setText(genre.name);
    }

    public void showGenreList() {
        this.genres = FileService.getGenres();

        int count = 1;
        for (Genre genre : this.genres) {
            if (genre != null) {
                if (count == 1) {
                    Genre activeGenre = ActiveGenre.getInstance().getGenre();

                    if (activeGenre != null) {
                        this.setSelectedGenre(activeGenre);
                    } else {
                        this.setSelectedGenre(genre);
                    }

                    count++;
                }

                this.genreList.getItems().add(genre);
            }
        }

        this.genreList.setCellFactory(new Callback<ListView<Genre>, ListCell<Genre>>() {
            @Override
            public ListCell<Genre> call(ListView<Genre> listView) {
                ListCell<Genre> cell = new ListCell<Genre>() {
                    @Override
                    public void updateItem(Genre genre, boolean empty) {
                        super.updateItem(genre, empty);
                        textProperty().unbind();

                        if (genre != null) {
                            textProperty().bind(Bindings.format("%s", genre.name));
                        }
                    }
                };

                cell.setOnMouseClicked(e -> {
                    if (!cell.isEmpty()) {
                        Genre genre = cell.getItem();
                        ActiveGenre activeGenre = ActiveGenre.getInstance();
                        activeGenre.setGenre(genre);

                        try {
                            SceneController.setScene("genre-view.fxml", "Genres");
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