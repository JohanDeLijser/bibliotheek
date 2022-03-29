package com.jdelijser.bibliotheek.services;

import com.jdelijser.bibliotheek.model.Book;

public final class ActiveBook {

    private static ActiveBook INSTANCE;
    private Book activeBook;

    private ActiveBook() {
    }

    public static ActiveBook getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new ActiveBook();
        }

        return INSTANCE;
    }

    public void setBook(Book book) {
        this.activeBook = book;
    }

    public Book getBook() {
        return this.activeBook;
    }
}
