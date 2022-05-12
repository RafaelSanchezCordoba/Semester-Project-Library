package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    private MultimediaItem multimediaItem;
    private Book book;
    private GenreList list = new GenreList();
    private Genre genre = new Genre("Fantasy");

    @BeforeEach
    void setUp() {
        list.addGenre(genre);
    }

    @Test void create_new_book_with_all_correct_parameters_for_constructor_one(){
        multimediaItem = new MultimediaItem("Wiedźmin","Supernova");
        book = new Book(multimediaItem.getTitle(),multimediaItem.getPublisher(),12345678,1,1992,list);
        assertEquals("Wiedźmin Supernova 12345678 1 1992 [Fantasy]",book.toStringForFirstConstructor());
    }

    @Test void create_new_book_with_all_correct_parameters_for_constructor_two(){
        multimediaItem = new MultimediaItem("Ender's Game","Supernova");
        book = new Book(0,multimediaItem.getTitle(),multimediaItem.getPublisher(),98765432);
        assertEquals("0 Ender's Game Supernova 98765432",book.toStringForSecondConstructor());
    }

    @Test void null_title_for_constructor_one(){
        multimediaItem = new MultimediaItem("Wiedźmin","Supernova");
        book = new Book(null,multimediaItem.getPublisher(),12345678,1,1992,list);
        assertEquals("null Supernova 12345678 1 1992 [Fantasy]",book.toStringForFirstConstructor());
    }
}