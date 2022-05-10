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
        System.out.println("Running tear down");
        book = null;
        assertNull(book);
    }
    
    @Test
    void getAuthor() {
        System.out.println("Getting author of the book");
        Book instance = new Book("","",1234,0,0);
        String expectedAuthor = "Andrzej Sapkowski";
        instance.setAuthor("Andrzej Sapkowski");
        String result = instance.getAuthor();
        assertEquals(expectedAuthor,result);
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