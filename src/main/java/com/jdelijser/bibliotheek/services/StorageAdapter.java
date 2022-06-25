package com.jdelijser.bibliotheek.services;

import com.jdelijser.bibliotheek.model.Author;
import com.jdelijser.bibliotheek.model.Book;
import com.jdelijser.bibliotheek.model.Genre;
import com.jdelijser.bibliotheek.model.Publisher;
import com.jdelijser.bibliotheek.storage.ActiveSource;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

public class StorageAdapter {
    public static final String STORAGE_METHOD_FILE = "file";
    public static final String STORAGE_METHOD_DATABASE = "database";

    public static ArrayList<String> getStorageTypes() {
        ArrayList<String> types = new ArrayList<>();

        types.add(STORAGE_METHOD_FILE);
        types.add(STORAGE_METHOD_DATABASE);

        return types;
    }

    public static void addBook(Book book) throws SQLException {
        if (StorageAdapter.isDatabase(StorageAdapter.getSource())) {
            DatabaseService.addBook(book);
            return;
        }

        FileService.addBook(book);
    }

    public static void addGenre(Genre genre) throws SQLException {
        if (StorageAdapter.isDatabase(StorageAdapter.getSource())) {
            DatabaseService.addGenre(genre);
            return;
        }

        FileService.writeToFile("genres", genre.name, genre);
    }

    public static void addAuthor(Author author) throws SQLException {
        if (StorageAdapter.isDatabase(StorageAdapter.getSource())) {
            DatabaseService.addAuthor(author);
            return;
        }

        FileService.writeToFile("authors", author.name, author);
    }

    public static void addPublisher(Publisher publisher) throws SQLException {
        if (StorageAdapter.isDatabase(StorageAdapter.getSource())) {
            DatabaseService.addPublisher(publisher);
            return;
        }

        FileService.writeToFile("publishers", publisher.name, publisher);
    }

    public static ArrayList<Book> getBooks() throws SQLException {
        if (StorageAdapter.isDatabase(StorageAdapter.getSource())) {
            return DatabaseService.getBooks();
        }

        return FileService.getBooks();
    }

    public static ArrayList<Genre> getGenres() throws SQLException {
        if (StorageAdapter.isDatabase(StorageAdapter.getSource())) {
            return DatabaseService.getGenres();
        }

        return FileService.getGenres();
    }

    public static ArrayList<Author> getAuthors() throws SQLException {
        if (StorageAdapter.isDatabase(StorageAdapter.getSource())) {
            return DatabaseService.getAuthors();
        }

        return FileService.getAuthors();
    }

    public static ArrayList<Publisher> getPublishers() throws SQLException {
        if (StorageAdapter.isDatabase(StorageAdapter.getSource())) {
            return DatabaseService.getPublishers();
        }

        return FileService.getPublishers();
    }

    public static void deleteActiveBook() throws SQLException, IOException {
        if (StorageAdapter.isDatabase(StorageAdapter.getSource())) {
            DatabaseService.deleteActiveBook();

            return;
        }

        FileService.deleteActiveBook();
    }

    public static void deleteActiveGenre() throws SQLException, IOException {
        if (StorageAdapter.isDatabase(StorageAdapter.getSource())) {
            try {
                DatabaseService.deleteActiveGenre();
            } catch (SQLException exception) {
                if (Objects.equals(1451, exception.getErrorCode())) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("You can't delete this genre because its linked to a book");
                    alert.show();
                }
            }

            return;
        }

        FileService.deleteActiveGenre();
    }

    public static void deleteActiveAuthor() throws SQLException, IOException {
        if (StorageAdapter.isDatabase(StorageAdapter.getSource())) {
            try {
                DatabaseService.deleteActiveAuthor();
            } catch (SQLException exception) {
                if (Objects.equals(1451, exception.getErrorCode())) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("You can't delete this author because its linked to a book");
                    alert.show();
                }
            }

            return;
        }

        FileService.deleteActiveAuthor();
    }

    public static void deleteActivePublisher() throws SQLException, IOException {
        if (StorageAdapter.isDatabase(StorageAdapter.getSource())) {
            try {
                DatabaseService.deleteActivePublisher();
            } catch (SQLException exception) {
                if (Objects.equals(1451, exception.getErrorCode())) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("You can't delete this publisher because its linked to a book");
                    alert.show();
                }
            }

            return;
        }

        FileService.deleteActivePublisher();
    }

    public static String getSource() {
        return ActiveSource.getInstance().getSource();
    }

    public static boolean isDatabase(String source) {
        return Objects.equals(source, STORAGE_METHOD_DATABASE);
    }
}
