package model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

class MagazineTest {
    private Magazine magazine;
    private CurrentTime currentTime;

    @BeforeEach
    void setUp() {
        System.out.println("--> setUp()");
        //magazine = new Magazine(1234,"Forbes","Forbes",12/2004,"Economy",null);
        }

    @AfterEach
    void tearDown() {
        System.out.println("<-- tearDown()");
    }

    @Test
    void getVolume() {
        System.out.println(magazine.getVolume());
    }

    @Test
    void getGenre() {
        System.out.println(magazine.getGenre());
    }

    @Test
    void getDate() {
        System.out.println(magazine.getDate());
    }
}