package services;

import com.jdelijser.bibliotheek.services.StorageAdapter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class StorageAdapterTest {

    @Test
    @DisplayName("Test if database source check works")
    void isDatabase() {
        assertTrue(StorageAdapter.isDatabase("database"));
    }

    @Test
    @DisplayName("Test if storage types contains all sources")
    void getStorageTypes() {
        ArrayList<String> sources = StorageAdapter.getStorageTypes();

        assertTrue(sources.contains("file"));
        assertTrue(sources.contains("database"));
    }
}
