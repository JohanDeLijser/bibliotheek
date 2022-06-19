package com.jdelijser.bibliotheek.services;

import com.jdelijser.bibliotheek.model.Author;
import com.jdelijser.bibliotheek.model.Book;
import com.jdelijser.bibliotheek.model.Genre;
import com.jdelijser.bibliotheek.model.Publisher;
import com.jdelijser.bibliotheek.storage.ActiveSource;

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
}
