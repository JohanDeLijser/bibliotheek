package com.jdelijser.bibliotheek.model;

import java.io.Serializable;

public class Author implements Serializable {
    public String name;

    public Author(String name) {
        this.name = name;
    }
}
