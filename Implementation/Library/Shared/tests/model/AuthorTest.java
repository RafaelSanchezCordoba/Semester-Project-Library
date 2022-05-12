package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthorTest {

    private Author author;

    @BeforeEach void setUp() {}

    @Test void create_new_author_with_all_correct_parameters(){
        author = new Author(0,"Andrzej","Sapkowski");
        assertEquals("0 Andrzej Sapkowski", author.toString());
    }

    @Test void null_first_name(){
        author = new Author(0,null,"Card");
        assertEquals("0 null Card",author.toString());
    }

    @Test void null_last_name(){
        author = new Author(2,"Orson Scott",null);
        assertEquals("2 Orson Scott null",author.toString());
    }

    @Test void get_id_returns_id(){
        author = new Author(3,"Orson Scott","Card");
        assertEquals(3,author.getId());
    }

    @Test void get_first_name_returns_first_name(){
        author = new Author(4,"Friedrich", "Nietzsche");
        assertEquals("Friedrich",author.getFirstName());
    }

    @Test void get_last_name_returns_last_name(){
        author = new Author(4,"Friedrich", "Nietzsche");
        assertEquals("Nietzsche",author.getLastName());
    }
}