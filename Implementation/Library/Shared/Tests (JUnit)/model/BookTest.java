package model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {
    private Book book;

    @BeforeEach
    void setUp() throws Exception{
        book = new Book("","",1234,0,0);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getIsbn() {
        System.out.println("Get isbn number");
        String expectedIsbn = "1234";
        //get cannot be tested without set

    }

    @Test
    void getEdition() {
    }

    @Test
    void getYear_published() {
    }

    @Test
    void getAuthor() {
    }

    @Test
    void getGenreList() {
    }

    @Test
    void setId() {
        System.out.println("Setting id number");
        int id = 1;
        book.setId(id);
        assertEquals(book.getId(), id);
    }

    @Test
    void setAuthor() {
        System.out.println("Setting author");
        String author = "Andrzej Sapkowski";
        book.setAuthor(author);
        assertEquals(book.getAuthor(), author);
    }
}