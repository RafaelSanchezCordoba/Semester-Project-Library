package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GenreTest {

    private Genre genre;

    @BeforeEach void setUp() {}

    @Test void create_new_genre_with_all_correct_parameters(){
        genre = new Genre("Fantasy");
        assertEquals("Fantasy", genre.toString());
    }

    @Test void null_genre(){
        genre = new Genre(null);
        assertNull(genre.toString());
    }

    @Test void get_genre(){
        genre = new Genre("Fantasy");
        assertEquals("Fantasy",genre.getGenre());
    }

    @Test void getId(){
        genre = new Genre("Fantasy");
        assertEquals(0,genre.getId());
    }

    @Test void setId(){
        genre = new Genre("Fantasy");
        genre.setId(1);
        assertEquals(1, genre.getId());
    }
}