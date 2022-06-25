package com.jdelijser.bibliotheek.controller;

import com.jdelijser.bibliotheek.model.Book;
import com.jdelijser.bibliotheek.services.StorageAdapter;
import com.jdelijser.bibliotheek.storage.ActiveBook;
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
    protected void changeSource() throws IOException {
        SceneController.setScene("source-view.fxml", "Change source");
    }

    @FXML
    protected void deleteBook() throws IOException, SQLException {
        StorageAdapter.deleteActiveBook();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            this.showBookList();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setSelectedBook(Book book) {
        this.bookTitle.setText(book.title);
        this.bookAuthor.setText(book.author);
        this.bookGenre.setText(book.genre);
        this.bookPublisher.setText(book.publisher);
        this.bookDate.setText(book.date.toString());
    }

    public void showBookList() throws SQLException {
        this.books = StorageAdapter.getBooks();

        int count = 1;
        for (Book book : this.books) {
            if (book != null) {
                if (count == 1) {
                    Book activeBook = ActiveBook.getInstance().getBook();

                    if (activeBook != null) {
                        this.setSelectedBook(activeBook);
                    } else {
                        this.setSelectedBook(book);
                    }

                    count++;
                }

                this.bookList.getItems().add(book);
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