package com.jdelijser.bibliotheek.controller;
import com.jdelijser.bibliotheek.model.Book;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class LibraryController implements Initializable {
    public ArrayList<Book> books = new ArrayList<>();

    @FXML
    protected ListView<String> bookList;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.showBookList();
    }

    private void showBookList() {
        this.books.add(new Book("Book title", "The author", "The genre", "The publisher", "january 2022"));
        this.books.add(new Book("Book title 2", "The author", "The genre", "The publisher", "january 2022"));
        this.books.add(new Book("Book title 3", "The author", "The genre", "The publisher", "january 2022"));
        this.books.add(new Book("Book title 4", "The author", "The genre", "The publisher", "january 2022"));
        this.books.add(new Book("Book title 5", "The author", "The genre", "The publisher", "january 2022"));

        for (Book book : this.books) {
            this.bookList.getItems().add(book.title);
        }
    }
}