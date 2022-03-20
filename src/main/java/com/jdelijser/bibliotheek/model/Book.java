package com.jdelijser.bibliotheek.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Book implements Serializable {
    public String title;
    public String author;
    public String genre;
    public String publisher;
    public LocalDate date;

    public Book(String title, String author, String genre, String publisher, LocalDate date) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.publisher = publisher;
        this.date = date;
    }
}
