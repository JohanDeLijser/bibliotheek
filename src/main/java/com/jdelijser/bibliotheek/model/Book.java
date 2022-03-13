package com.jdelijser.bibliotheek.model;

public class Book {
    public String title;
    public Author author;
    public Genre genre;
    public Publisher publisher;
    public String date;

    public Book(String title, Author author, Genre genre, Publisher publisher, String date) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.publisher = publisher;
        this.date = date;
    }
}
