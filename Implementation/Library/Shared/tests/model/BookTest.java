package model;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    private Book book;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        System.out.println("->> setUp()");
//        book = new Book(1234,"Wiedźmin",null);
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        System.out.println("<-- tearDown()");
    }

    @org.junit.jupiter.api.Test
    void setAuthor() {
        System.out.println("  - setAuthor()");
        book.setAuthor("Supernova");
    }
}