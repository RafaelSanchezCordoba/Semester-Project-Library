package model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GenreListTest {
    private ArrayList<Genre> genreList = new ArrayList<>();
    private Genre genre1, genre2, genre3;

    @BeforeEach
    void setUp() {
        genre1 = new Genre("Fantasy");
        genre2 = new Genre("Horror");
        genre3 = new Genre("Action and Adventure");
        genreList.add(1,genre1);
        genreList.add(2,genre2);
        genreList.add(3,genre3);
    }

    @AfterEach
    void tearDown() {
        System.out.println("Running tear down");
        assertNull(genreList);
    }

    @Test
    void addGenre() {
        System.out.println("Running add genre test");
        String genreIndex0 = genreList.get(0).toString();
        String genreIndex1 = genreList.get(1).toString();
        String genreIndex2 = genreList.get(2).toString();
        assertEquals("Fantasy",genreIndex0);
        assertEquals("Horror",genreIndex1);
        assertEquals("Action and Adventure", genreIndex2);
    }

    @Test void nullAdd(){
        System.out.println("Adding genre which is null");
        assertNull(assertThrows(NullPointerException.class, () -> genreList.add(4,null)));
    }
}