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
        book=new Book("Laini Taylor" ,"Daughter of Smoke and Bone","Hodder & Stoughton","978-0-316-13402-6",2,2011,genres);
        assertEquals("Title: Daughter of Smoke and Bone, author: Laini Taylor, edition=2, year_published=2011, isbn='978-0-316-13402-6', genres=Fantasy Juvenile Romance",book.toString());
    }

    @Test void create_a_book_without_author()
    {
        GenreList genres=new GenreList();
        genres.addGenre(new Genre("Fantasy"));
        genres.addGenre(new Genre("Juvenile"));
        genres.addGenre(new Genre("Romance"));
        book=new Book("Daughter of Smoke and Bone","Hodder & Stoughton","978-0-316-13402-6",2,2011,genres);
        assertEquals("Title: Daughter of Smoke and Bone, author: Anonymous, edition=2, year_published=2011, isbn='978-0-316-13402-6', genres=Fantasy Juvenile Romance",book.toString());
    }

    @Test void create_a_book_without_genre()
    {
        book=new Book("Laini Taylor", "Daughter of Smoke and Bone","Hodder & Stoughton","978-0-316-13402-6",2,2011);
        assertEquals("Title: Daughter of Smoke and Bone, author: Laini Taylor, edition=2, year_published=2011, isbn='978-0-316-13402-6', genres=null",book.toString());
    }

    @Test void create_a_book_without_genre_or_author()
    {
        book=new Book("Daughter of Smoke and Bone","Hodder & Stoughton","978-0-316-13402-6",2,2011);
        assertEquals("Title: Daughter of Smoke and Bone, author: Anonymous, edition=2, year_published=2011, isbn='978-0-316-13402-6', genres=null",book.toString());

    }

    @Test void set_author_sets_the_author()
    {
        book=new Book("Daughter of Smoke and Bone","Hodder & Stoughton","978-0-316-13402-6",2,2011);
       book.setAuthor("Laini Taylor");
        assertEquals("Title: Daughter of Smoke and Bone, author: Laini Taylor, edition=2, year_published=2011, isbn='978-0-316-13402-6', genres=null",book.toString());
    }

    @Test void set_the_author_to_null_sets_anonymous()
    {
        book=new Book("Laini Taylor","Daughter of Smoke and Bone","Hodder & Stoughton","978-0-316-13402-6",2,2011);
        book.setAuthor(null);
        assertEquals("Title: Daughter of Smoke and Bone, author: Anonymous, edition=2, year_published=2011, isbn='978-0-316-13402-6', genres=null",book.toString());
    }

    @Test void get_isbn()
    {
        GenreList genres=new GenreList();
        genres.addGenre(new Genre("Fantasy"));
        genres.addGenre(new Genre("Juvenile"));
        genres.addGenre(new Genre("Romance"));
        book=new Book("Laini Taylor" ,"Daughter of Smoke and Bone","Hodder & Stoughton","978-0-316-13402-6",2,2011,genres);
        assertEquals("978-0-316-13402-6",book.getIsbn());
    }

    @Test void get_Author()
    {
        GenreList genres=new GenreList();
        genres.addGenre(new Genre("Fantasy"));
        genres.addGenre(new Genre("Juvenile"));
        genres.addGenre(new Genre("Romance"));
        book=new Book("Laini Taylor" ,"Daughter of Smoke and Bone","Hodder & Stoughton","978-0-316-13402-6",2,2011,genres);
        assertEquals("Laini Taylor",book.getAuthor());
    }


    @Test void get_Author_when_anonymous()
    {
        GenreList genres=new GenreList();
        genres.addGenre(new Genre("Fantasy"));
        genres.addGenre(new Genre("Juvenile"));
        genres.addGenre(new Genre("Romance"));
        book=new Book("Daughter of Smoke and Bone","Hodder & Stoughton","978-0-316-13402-6",2,2011,genres);
        assertEquals("Anonymous",book.getAuthor());
    }

    @Test void get_Year_Published()
    {
        GenreList genres=new GenreList();
        genres.addGenre(new Genre("Fantasy"));
        genres.addGenre(new Genre("Juvenile"));
        genres.addGenre(new Genre("Romance"));
        book=new Book("Laini Taylor" ,"Daughter of Smoke and Bone","Hodder & Stoughton","978-0-316-13402-6",2,2011,genres);
        assertEquals(2011,book.getYear_published());
    }

    @Test void get_Edition()
    {
        GenreList genres=new GenreList();
        genres.addGenre(new Genre("Fantasy"));
        genres.addGenre(new Genre("Juvenile"));
        genres.addGenre(new Genre("Romance"));
        book=new Book("Laini Taylor" ,"Daughter of Smoke and Bone","Hodder & Stoughton","978-0-316-13402-6",2,2011,genres);
        assertEquals(2,book.getEdition());
    }

    @Test void set_id_to_0()
    {
        GenreList genres=new GenreList();
        genres.addGenre(new Genre("Fantasy"));
        genres.addGenre(new Genre("Juvenile"));
        genres.addGenre(new Genre("Romance"));
        book=new Book("Laini Taylor" ,"Daughter of Smoke and Bone","Hodder & Stoughton","978-0-316-13402-6",2,2011,genres);
        assertEquals(0,book.getId());
        book.setId(1);
        assertEquals(1,book.getId());
    }



}

