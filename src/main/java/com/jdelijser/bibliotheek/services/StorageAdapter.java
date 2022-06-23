package com.jdelijser.bibliotheek.services;

import com.jdelijser.bibliotheek.model.Author;
import com.jdelijser.bibliotheek.model.Book;
import com.jdelijser.bibliotheek.model.Genre;
import com.jdelijser.bibliotheek.model.Publisher;
import com.jdelijser.bibliotheek.storage.ActiveSource;

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
        if (Objects.equals(ActiveSource.getInstance().getSource(), STORAGE_METHOD_DATABASE)) {
            DatabaseService.addBook(book);
            return;
        }

        FileService.addBook(book);
    }

    public static void addGenre(Genre genre) throws SQLException {
        if (Objects.equals(ActiveSource.getInstance().getSource(), STORAGE_METHOD_DATABASE)) {
            DatabaseService.addGenre(genre);
            return;
        }

        FileService.writeToFile("genres", genre.name, genre);
    }

    public static void addAuthor(Author author) throws SQLException {
        if (Objects.equals(ActiveSource.getInstance().getSource(), STORAGE_METHOD_DATABASE)) {
            DatabaseService.addAuthor(author);
            return;
        }

        FileService.writeToFile("authors", author.name, author);
    }

    public static void addPublisher(Publisher publisher) throws SQLException {
        if (Objects.equals(ActiveSource.getInstance().getSource(), STORAGE_METHOD_DATABASE)) {
            DatabaseService.addPublisher(publisher);
            return;
        }

        FileService.writeToFile("publishers", publisher.name, publisher);
    }

    public static ArrayList<Book> getBooks() throws SQLException {
        if (Objects.equals(ActiveSource.getInstance().getSource(), STORAGE_METHOD_DATABASE)) {
            return DatabaseService.getBooks();
        }

        return FileService.getBooks();
    }

    public static ArrayList<Genre> getGenres() throws SQLException {
        if (Objects.equals(ActiveSource.getInstance().getSource(), STORAGE_METHOD_DATABASE)) {
            return DatabaseService.getGenres();
        }

        return FileService.getGenres();
    }

    public static ArrayList<Author> getAuthors() throws SQLException {
        if (Objects.equals(ActiveSource.getInstance().getSource(), STORAGE_METHOD_DATABASE)) {
            return DatabaseService.getAuthors();
        }

        return FileService.getAuthors();
    }

    public static ArrayList<Publisher> getPublishers() throws SQLException {
        if (Objects.equals(ActiveSource.getInstance().getSource(), STORAGE_METHOD_DATABASE)) {
            return DatabaseService.getPublishers();
        }

        return FileService.getPublishers();
    }

    public static void deleteActiveBook() throws SQLException, IOException {
        if (Objects.equals(ActiveSource.getInstance().getSource(), STORAGE_METHOD_DATABASE)) {
            DatabaseService.deleteActiveBook();
            return;
        }

        FileService.deleteActiveBook();
    }

    public static void deleteActiveGenre() throws SQLException, IOException {
        if (Objects.equals(ActiveSource.getInstance().getSource(), STORAGE_METHOD_DATABASE)) {
            DatabaseService.deleteActiveGenre();
            return;
        }

        FileService.deleteActiveGenre();
    }

    public static void deleteActiveAuthor() throws SQLException, IOException {
        if (Objects.equals(ActiveSource.getInstance().getSource(), STORAGE_METHOD_DATABASE)) {
            DatabaseService.deleteActiveAuthor();
            return;
        }

        FileService.deleteActiveAuthor();
    }

    public static void deleteActivePublisher() throws SQLException, IOException {
        if (Objects.equals(ActiveSource.getInstance().getSource(), STORAGE_METHOD_DATABASE)) {
            DatabaseService.deleteActivePublisher();
            return;
        }

        FileService.deleteActivePublisher();
    }
}
