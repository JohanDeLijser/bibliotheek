package com.jdelijser.bibliotheek.controller;

import com.jdelijser.bibliotheek.model.Book;
import com.jdelijser.bibliotheek.services.FileService;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.time.LocalDate;

public class AddBookController {
    @FXML
    public TextField bookTitle;
    @FXML
    public TextField bookAuthor;
    @FXML
    public TextField bookGenre;
    @FXML
    public TextField bookPublisher;
    @FXML
    public DatePicker bookDate;

    @FXML
    protected void addBook() throws IOException {
        String title = this.bookTitle.getText();
        String author = this.bookAuthor.getText();
        String genre = this.bookGenre.getText();
        String publisher = this.bookPublisher.getText();
        LocalDate date = this.bookDate.getValue();

        if (!title.equals("") &&
            !author.equals("") &&
            !genre.equals("") &&
            !publisher.equals("") &&
            date != null
        ) {
            Book book = new Book(title, author, genre, publisher, date);

            FileService.writeToFile("book", book.title + " " + book.author, book);

            SceneController.toMain();
        }
    }

    @FXML
    protected void cancelBook() throws IOException {
        SceneController.toMain();
    }
}