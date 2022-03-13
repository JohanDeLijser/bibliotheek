package com.jdelijser.bibliotheek.model;

public class Book {
    public String title;
    public String author;
    public String genre;
    public String publisher;
    public String date;

    public Book(String title, String author, String genre, String publisher, String date) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.publisher = publisher;
        this.date = date;
    }
}
