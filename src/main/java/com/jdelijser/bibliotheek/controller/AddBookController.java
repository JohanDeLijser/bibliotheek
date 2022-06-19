package com.jdelijser.bibliotheek.controller;

import com.jdelijser.bibliotheek.model.Author;
import com.jdelijser.bibliotheek.model.Book;
import com.jdelijser.bibliotheek.model.Genre;
import com.jdelijser.bibliotheek.model.Publisher;
import com.jdelijser.bibliotheek.services.FileService;
import com.jdelijser.bibliotheek.services.StorageAdapter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddBookController implements Initializable {
    @FXML
    public TextField bookTitle;
    @FXML
    public ChoiceBox<String> bookAuthor;
    @FXML
    public ChoiceBox<String> bookGenre;
    @FXML
    public ChoiceBox<String> bookPublisher;
    @FXML
    public DatePicker bookDate;

    @FXML
    protected void addBook() throws IOException, SQLException {
        String title = this.bookTitle.getText();
        String author = this.bookAuthor.getValue();
        String genre = this.bookGenre.getValue();
        String publisher = this.bookPublisher.getValue();
        LocalDate date = this.bookDate.getValue();

        if (!title.equals("") &&
            !author.equals("") &&
            !genre.equals("") &&
            !publisher.equals("") &&
            date != null
        ) {
            Book book = new Book(title, author, genre, publisher, date);

            StorageAdapter.addBook(book);

            SceneController.toMain();
        }
    }

    @FXML
    protected void cancelBook() throws IOException {
        SceneController.toMain();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            this.addGenresToSelect();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            this.addAuthorsToSelect();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            this.addPublishersToSelect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addGenresToSelect() throws SQLException {
        ArrayList<Genre> genres = StorageAdapter.getGenres();
        ObservableList<String> genreTitles = FXCollections.observableArrayList();;

        for(Genre genre : genres) {
            genreTitles.add(genre.name);
        }

        this.bookGenre.setItems(genreTitles);
    }

    public void addAuthorsToSelect() throws SQLException {
        ArrayList<Author> authors = StorageAdapter.getAuthors();
        ObservableList<String> authorTitles = FXCollections.observableArrayList();;

        for(Author author : authors) {
            authorTitles.add(author.name);
        }

        this.bookAuthor.setItems(authorTitles);
    }

    public void addPublishersToSelect() throws SQLException {
        ArrayList<Publisher> publishers = StorageAdapter.getPublishers();
        ObservableList<String> publisherTitles = FXCollections.observableArrayList();;

        for(Publisher publisher : publishers) {
            publisherTitles.add(publisher.name);
        }

        this.bookPublisher.setItems(publisherTitles);
    }
}