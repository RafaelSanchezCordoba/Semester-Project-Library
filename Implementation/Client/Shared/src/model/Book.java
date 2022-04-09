package model;

import java.io.Serializable;

/**
 * Sub-class Book that extends <code>"MultimediaItem"</code>  and implements <code>Serializable</code> 
 */
public class Book extends MultimediaItem implements Serializable {
    private final String isbn;
    private final int edition, year_published;
    private Author author;
    private GenreList genreList;


    public Book(int id, String title, String publisher, String isbn, int edition, int year_published, GenreList genreList) {
        super(id, title, publisher);
        this.isbn = isbn;
        this.edition = edition;
        this.year_published = year_published;
        this.author = null;
        this.genreList = genreList;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
