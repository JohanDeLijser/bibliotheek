package com.jdelijser.bibliotheek.storage;

import com.jdelijser.bibliotheek.model.Author;

public final class ActiveAuthor {

    private static ActiveAuthor INSTANCE;
    private Author activeAuthor;

    private ActiveAuthor() {
    }

    public static ActiveAuthor getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new ActiveAuthor();
        }

        return INSTANCE;
    }

    public void setAuthor(Author author) {
        this.activeAuthor = author;
    }

    public Author getAuthor() {
        return this.activeAuthor;
    }
}
