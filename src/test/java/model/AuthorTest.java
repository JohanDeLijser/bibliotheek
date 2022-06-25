package model;

import com.jdelijser.bibliotheek.model.Author;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthorTest {

    private Author author;

    @BeforeEach
    void setUp() {
        this.author = new Author("author name");
    }

    @Test
    @DisplayName("Test if authors name gets saved correctly in model")
    void authorName() {
        assertEquals("author name", author.name);
    }
}
