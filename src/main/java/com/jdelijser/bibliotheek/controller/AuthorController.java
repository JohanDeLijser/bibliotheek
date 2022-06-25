package com.jdelijser.bibliotheek.controller;

import com.jdelijser.bibliotheek.model.Author;
import com.jdelijser.bibliotheek.services.StorageAdapter;
import com.jdelijser.bibliotheek.storage.ActiveAuthor;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AuthorController implements Initializable {
    public ArrayList<Author> authors = new ArrayList<>();

    @FXML
    protected ListView<Author> authorList;

    @FXML
    public Label authorTitle;

    @FXML
    protected void backToLibrary() throws IOException {
        SceneController.toMain();
    }

    @FXML
    protected void addAuthor() throws IOException {
        SceneController.setScene("add-author-view.fxml", "Add book");
    }

    @FXML
    protected void deleteAuthor() throws IOException, SQLException {
        StorageAdapter.deleteActiveAuthor();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            this.showAuthorList();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setSelectedAuthor(Author author) {
        this.authorTitle.setText(author.name);
    }

    public void showAuthorList() throws SQLException {
        this.authors = StorageAdapter.getAuthors();

        int count = 1;
        for (Author author : this.authors) {
            if (author != null) {
                if (count == 1) {
                    ActiveAuthor activeAuthor = ActiveAuthor.getInstance();

                    if (activeAuthor.getAuthor() != null) {
                        this.setSelectedAuthor(activeAuthor.getAuthor());
                    } else {
                        this.setSelectedAuthor(author);
                        activeAuthor.setAuthor(author);
                    }

                    count++;
                }

                this.authorList.getItems().add(author);
            }
        }

        this.authorList.setCellFactory(new Callback<ListView<Author>, ListCell<Author>>() {
            @Override
            public ListCell<Author> call(ListView<Author> listView) {
                ListCell<Author> cell = new ListCell<Author>() {
                    @Override
                    public void updateItem(Author author, boolean empty) {
                        super.updateItem(author, empty);
                        textProperty().unbind();

                        if (author != null) {
                            textProperty().bind(Bindings.format("%s", author.name));
                        }
                    }
                };

                cell.setOnMouseClicked(e -> {
                    if (!cell.isEmpty()) {
                        Author author = cell.getItem();
                        ActiveAuthor activeAuthor = ActiveAuthor.getInstance();
                        activeAuthor.setAuthor(author);

                        try {
                            SceneController.setScene("author-view.fxml", "Authors");
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