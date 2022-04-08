package model.multimediaItem;

import model.Author;

public class Book extends MultimediaItem{
    private final String isbn;
    private final int edition, year_published;
    private Author author;


    public Book(int id, String title, String publisher, String isbn, int edition, int year_published) {
        super(id, title, publisher);
        this.isbn = isbn;
        this.edition = edition;
        this.year_published = year_published;
        this.author = null;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
