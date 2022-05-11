package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GenreListTest {

    private GenreList list;

    @BeforeEach void setUp() {
        list = new GenreList();
    }

    @Test void the_list_is_created(){
        assertEquals(new ArrayList<Genre>(),list.getGenresList());
    }

    @Test void add_one_genre(){
        Genre g1 = new Genre("genre1");
        list.addGenre(g1);
        assertEquals(g1, list.getGenresList().get(0));
    }

    @Test void add_two_genres(){
        Genre g1 = new Genre("genre1");
        Genre g2 = new Genre("genre2");
        ArrayList<Genre> testList = new ArrayList<>();
        testList.add(g1);
        testList.add(g2);
        list.addGenre(g1);
        list.addGenre(g2);
        assertEquals(testList, list.getGenresList());
    }

    @Test void add_multiple_genres(){
        Genre g1 = new Genre("genre1");
        Genre g2 = new Genre("genre2");
        Genre g3 = new Genre("genre3");
        Genre g4 = new Genre("genre4");
        Genre g5 = new Genre("genre5");
        Genre g6 = new Genre("genre6");
        ArrayList<Genre> testList = new ArrayList<>();
        testList.add(g1);
        testList.add(g2);
        testList.add(g3);
        testList.add(g4);
        testList.add(g5);
        testList.add(g6);
        list.addGenre(g1);
        list.addGenre(g2);
        list.addGenre(g3);
        list.addGenre(g4);
        list.addGenre(g5);
        list.addGenre(g6);
        assertEquals(testList,list.getGenresList());
    }

    @Test void remove_genre(){
        Genre g1 = new Genre("genre1");
        Genre g2 = new Genre("genre2");
        ArrayList<Genre> testList = new ArrayList<>();
        testList.add(g1);
        list.addGenre(g1);
        list.addGenre(g2);
        list.removeGenre(g2);
        assertEquals(testList,list.getGenresList());
    }

    @Test void remove_a_genre_not_on_the_list(){
        Genre g1 = new Genre("genre1");
        Genre g2 = new Genre("genre2");
        Genre g3 = new Genre("genre3");
        ArrayList<Genre> testList = new ArrayList<>();
        testList.add(g1);
        testList.add(g2);
        list.addGenre(g1);
        list.addGenre(g2);
        list.removeGenre(g3);
        assertEquals(testList,list.getGenresList());
    }
}