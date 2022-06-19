package com.jdelijser.bibliotheek.storage;

import com.jdelijser.bibliotheek.model.Genre;

public final class ActiveGenre {

    private static ActiveGenre INSTANCE;
    private Genre activeGenre;

    private ActiveGenre() {
    }

    public static ActiveGenre getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new ActiveGenre();
        }

        return INSTANCE;
    }

    public void setGenre(Genre genre) {
        this.activeGenre = genre;
    }

    public Genre getGenre() {
        return this.activeGenre;
    }
}
