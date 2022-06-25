package model;

import com.jdelijser.bibliotheek.model.Genre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GenreTest {

    private Genre genre;

    @BeforeEach
    void setUp() {
        this.genre = new Genre("genre name");
    }

    @Test
    @DisplayName("Test if genres name gets saved correctly in model")
    void genreName() {
        assertEquals("genre name", genre.name);
    }
}
