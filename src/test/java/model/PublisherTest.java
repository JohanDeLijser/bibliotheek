package model;

import com.jdelijser.bibliotheek.model.Publisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PublisherTest {

    private Publisher publisher;

    @BeforeEach
    void setUp() {
        this.publisher = new Publisher("publisher name");
    }

    @Test
    @DisplayName("Test if publishers name gets saved correctly in model")
    void publisherName() {
        assertEquals("publisher name", publisher.name);
    }
}
