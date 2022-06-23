package com.jdelijser.bibliotheek.services;

import com.jdelijser.bibliotheek.model.Author;
import com.jdelijser.bibliotheek.model.Book;
import com.jdelijser.bibliotheek.model.Genre;
import com.jdelijser.bibliotheek.model.Publisher;
import com.jdelijser.bibliotheek.storage.ActiveAuthor;
import com.jdelijser.bibliotheek.storage.ActiveBook;
import com.jdelijser.bibliotheek.storage.ActiveGenre;
import com.jdelijser.bibliotheek.storage.ActivePublisher;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class DatabaseService {
    private static Statement getStatement() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dev_bibliotheek", "root", null);
        return connection.createStatement();
    }

    public static void addBook(Book book) throws SQLException {
        int genreId = DatabaseService.getAttributeIdByName("genres", book.genre);
        int authorId = DatabaseService.getAttributeIdByName("authors", book.author);
        int publisherId = DatabaseService.getAttributeIdByName("publishers", book.publisher);

        if (genreId != 0 || authorId != 0 || publisherId != 0) {
            DatabaseService
                    .getStatement()
                    .execute(
                            "insert into books (title, genre_id, author_id, publisher_id, date) VALUES ('"+book.title+"',"+genreId+","+authorId+","+publisherId+",'"+book.date+"')"
                    );
        } else {
            throw new Error("Can't add book because meta data doesnt match");
        }
    }

    public static void addGenre(Genre genre) throws SQLException {
        DatabaseService.getStatement().execute("insert into genres (name) VALUES ('" + genre.name + "')");
    }

    public static void addAuthor(Author author) throws SQLException {
        DatabaseService.getStatement().execute("insert into authors (name) VALUES ('" + author.name + "')");
    }

    public static void addPublisher(Publisher publisher) throws SQLException {
        DatabaseService.getStatement().execute("insert into publishers (name) VALUES ('" + publisher.name + "')");
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

    public static void deleteActiveBook() throws SQLException {
        Book activeBook = ActiveBook.getInstance().getBook();
        int genreId = DatabaseService.getAttributeIdByName("genres", activeBook.genre);
        int authorId = DatabaseService.getAttributeIdByName("authors", activeBook.author);
        int publisherId = DatabaseService.getAttributeIdByName("publishers", activeBook.publisher);

        DatabaseService
                .getStatement()
                .execute("DELETE from books WHERE title = '" + activeBook.title + "' AND genre_id = " + genreId + " AND author_id = " + authorId + " AND publisher_id = " + publisherId);
    }

    public static void deleteActiveGenre() throws SQLException {
        Genre activeGenre = ActiveGenre.getInstance().getGenre();

        DatabaseService.getStatement().execute("DELETE from genres WHERE name = '" + activeGenre.name + "'");
    }

    public static void deleteActiveAuthor() throws SQLException {
        Author activeAuthor = ActiveAuthor.getInstance().getAuthor();

        DatabaseService.getStatement().execute("DELETE from authors WHERE name = '" + activeAuthor.name + "'");
    }

    public static void deleteActivePublisher() throws SQLException {
        Publisher activePublisher = ActivePublisher.getInstance().getPublisher();

        DatabaseService.getStatement().execute("DELETE from publishers WHERE name = '" + activePublisher.name + "'");
    }

    public static String getAttributeById(String attribute, int id) throws SQLException {
        ResultSet resultSet = DatabaseService.getStatement().executeQuery("select name from " + attribute + " where ID = " + id);

        while (resultSet.next()) {
            return resultSet.getString("name");
        }

        return "";
    }

    public static int getAttributeIdByName(String attribute, String name) throws SQLException {
        ResultSet resultSet = DatabaseService.getStatement().executeQuery("select ID from " + attribute + " where name = '" + name + "'");

        while (resultSet.next()) {
            return resultSet.getInt("ID");
        }

        return 0;
    }
}
