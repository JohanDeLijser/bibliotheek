package model;

import com.jdelijser.bibliotheek.model.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookTest {

    private Book book;

    @BeforeEach
    void setUp() {
        this.book = new Book("book title", "book author", "book genre", "book publisher", LocalDate.ofYearDay(2022, 1));
    }

    @Test
    @DisplayName("Test if books title gets saved correctly in model")
    void bookTitle() {
        assertEquals("book title", book.title);
    }

    @Test
    @DisplayName("Test if books author gets saved correctly in model")
    void bookAuthor() {
        assertEquals("book author", book.author);
    }

    @Test
    @DisplayName("Test if books genre gets saved correctly in model")
    void bookGenre() {
        assertEquals("book genre", book.genre);
    }

    @Test
    @DisplayName("Test if books publisher gets saved correctly in model")
    void bookPublisher() {
        assertEquals("book publisher", book.publisher);
    }

    @Test
    @DisplayName("Test if books date gets saved correctly in model")
    void bookDate() {
        assertEquals(LocalDate.ofYearDay(2022, 1), book.date);
    }
}
