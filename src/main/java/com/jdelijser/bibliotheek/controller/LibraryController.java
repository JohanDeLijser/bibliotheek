package com.jdelijser.bibliotheek.controller;

import com.jdelijser.bibliotheek.model.Book;
import com.jdelijser.bibliotheek.services.ActiveBook;
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

public class LibraryController implements Initializable {
    public ArrayList<Book> books = new ArrayList<>();

    @FXML
    protected ListView<Book> bookList;

    @FXML
    public Label bookTitle;
    @FXML
    public Label bookAuthor;
    @FXML
    public Label bookGenre;
    @FXML
    public Label bookPublisher;
    @FXML
    public Label bookDate;

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
    protected void openAddBook() throws IOException {
        SceneController.setScene("add-book-view.fxml", "Add book");
    }

    @FXML
    protected void deleteBook() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.showBookList();
    }

    public void setSelectedBook(Book book) {
        this.bookTitle.setText(book.title);
        this.bookAuthor.setText(book.author);
        this.bookGenre.setText(book.genre);
        this.bookPublisher.setText(book.publisher);
        this.bookDate.setText(book.date.toString());
    }

    public void showBookList() {
        this.books = FileService.getBooks();

        int count = 1;
        for (Book book : this.books) {
            if (book != null) {
                this.bookList.getItems().add(book);

                if (count == 1) {
                    ActiveBook activeBook = ActiveBook.getInstance();
                    Book activeBookItem = activeBook.getBook();

                    if (activeBookItem != null) {
                        this.setSelectedBook(activeBookItem);
                    } else {
                        this.setSelectedBook(book);
                    }

                    count++;
                }
            }
        }

        this.bookList.setCellFactory(new Callback<ListView<Book>, ListCell<Book>>() {
            @Override
            public ListCell<Book> call(ListView<Book> listView) {
                ListCell<Book> cell = new ListCell<Book>() {
                    @Override
                    public void updateItem(Book book, boolean empty) {
                        super.updateItem(book, empty);
                        textProperty().unbind();

                        if (book != null) {
                            textProperty().bind(Bindings.format("%s: %s", book.author, book.title));
                        }
                    }
                };

                cell.setOnMouseClicked(e -> {
                    if (!cell.isEmpty()) {
                        Book book = cell.getItem();
                        ActiveBook activeBook = ActiveBook.getInstance();
                        activeBook.setBook(book);

                        try {
                            SceneController.toMain();
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