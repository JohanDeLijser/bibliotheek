package com.jdelijser.bibliotheek.services;

import com.jdelijser.bibliotheek.model.Author;
import com.jdelijser.bibliotheek.model.Book;
import com.jdelijser.bibliotheek.model.Genre;
import com.jdelijser.bibliotheek.model.Publisher;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class DatabaseService {
    private static Statement getStatement() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dev_bibliotheek", "root", null);
        return connection.createStatement();
    }

    public static ArrayList<Book> getBooks() throws SQLException {
        ArrayList<Book> books = new ArrayList<>();
        Statement statement = DatabaseService.getStatement();
        ResultSet resultSet = statement.executeQuery("select * from books");

        while (resultSet.next()) {
            String title =  resultSet.getString("title");
            String genre = DatabaseService.getAttributeById("genres", resultSet.getInt("genre_id"));
            String author = DatabaseService.getAttributeById("authors", resultSet.getInt("author_id"));
            String publisher = DatabaseService.getAttributeById("publishers", resultSet.getInt("publisher_id"));
            LocalDate date = resultSet.getDate("date").toLocalDate();

            books.add(new Book(title, author, genre, publisher, date));
        }

        return books;
    }

    public static ArrayList<Genre> getGenres() throws SQLException {
        ArrayList<Genre> genres = new ArrayList<>();
        ResultSet resultSet = DatabaseService.getStatement().executeQuery("select * from genres");

        while (resultSet.next()) {
            genres.add(new Genre(resultSet.getString("name")));
        }

        return genres;
    }

    public static ArrayList<Author> getAuthors() throws SQLException {
        ArrayList<Author> authors = new ArrayList<>();
        ResultSet resultSet = DatabaseService.getStatement().executeQuery("select * from authors");

        while (resultSet.next()) {
            authors.add(new Author(resultSet.getString("name")));
        }

        return authors;
    }

    public static ArrayList<Publisher> getPublishers() throws SQLException {
        ArrayList<Publisher> authors = new ArrayList<>();
        ResultSet resultSet = DatabaseService.getStatement().executeQuery("select * from publishers");

        while (resultSet.next()) {
            authors.add(new Publisher(resultSet.getString("name")));
        }

        return authors;
    }

    public static String getAttributeById(String attribute, int id) throws SQLException {
        ResultSet resultSet = DatabaseService.getStatement().executeQuery("select name from " + attribute + " where ID = " + id);

        while (resultSet.next()) {
            return resultSet.getString("name");
        }

        return "";
    }
}
