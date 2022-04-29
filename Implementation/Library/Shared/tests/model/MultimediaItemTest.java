package model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MultimediaItemTest {

    private MultimediaItem multimediaItem;

    @BeforeEach
    void setUp() {
        System.out.println("--> setUp()");
        multimediaItem = new MultimediaItem(1234,"Wiedźmin","Supernova");
    }

    @AfterEach
    void tearDown() {
        System.out.println("<-- tearDown()");
    }

    @Test
    void setId() {
        multimediaItem.setId(4321);
    }
}