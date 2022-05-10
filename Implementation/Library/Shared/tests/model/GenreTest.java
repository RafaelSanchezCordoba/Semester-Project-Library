package model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GenreTest {
    private Genre genre;

    @BeforeEach
    void setUp() {
        genre = new Genre("Fantasy");
    }

    @AfterEach
    void tearDown() {
        System.out.println("Running tear down");
        genre = null;
        assertNull(genre);
    }

    @Test
    void getGenre() {
        System.out.println("Getting a genre type of the book");
        String expectedGenre = "Fantasy";
        String result = genre.getGenre();
        assertEquals(expectedGenre,result);
    }

    @Test
    void setId() {
        System.out.println("Setting id number");
        int id = 1;
        genre.setId(id);
        assertEquals(genre.getId(), id);
    }

    @Test
    void getId() {
        System.out.println("Getting id number");
        int expectedId = 2;
        int result = genre.getId();
        assertEquals(expectedId, result);
    }

}