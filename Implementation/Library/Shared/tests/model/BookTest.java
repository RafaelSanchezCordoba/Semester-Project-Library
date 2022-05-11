package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    private Book book;

    @Test void create_a_new_book_with_all_correct_parameters()
    {
        GenreList genres=new GenreList();
        genres.addGenre(new Genre("Fantasy"));
        genres.addGenre(new Genre("Juvenile"));
        genres.addGenre(new Genre("Romance"));
        book=new Book("Daughter of Smoke and Bone","Hodder & Stoughton","978-0-316-13402-6",2,2011,genres);
        assertEquals("",book.toString());
    }

}