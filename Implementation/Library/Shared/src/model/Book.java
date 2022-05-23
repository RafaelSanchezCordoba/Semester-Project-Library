package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Sub-class Book that extends <code>"MultimediaItem"</code>  and implements <code>Serializable</code>.
 * @author Rafael Sánchez Córdoba.
 * @version 1.0 - 08/04/22.
 */
public class Book extends MultimediaItem implements Serializable {
    private final int  edition, year_published;
    private String isbn,author;
    private GenreList genreList;

    /**
     * Book constructor with super method.
     * The author is set to null because it could be an anonymous writer.
     * @param title
     * The title of the book.
     * @param publisher
     * The publisher of the book.
     * @param isbn
     * The isbn of the book.
     * @param edition
     * The edition of the book.
     * @param year_published
     * The year published of the book.
     * @param genreList
     * The list of genre of the book.
     */
    public Book(String title, String publisher, String isbn, int edition, int year_published, GenreList genreList) {
        super(title, publisher);
        this.isbn = isbn;
        this.edition = edition;
        this.year_published = year_published;
        this.genreList = genreList;
        setAuthor("Anonymous");
    }

    /**
     * Book constructor with super method and al the variables as an arguments
     * @param author
     * The author of the book
     * @param title
     * The title of the book
     * @param publisher
     * The publisher of the book
     * @param isbn
     * The isbn of the book
     * @param edition
     * The edition of the book
     * @param year_published
     * The year published of the book
     * @param genreList
     * The genres of the book
     */
    public Book(String author,String title, String publisher, String isbn, int edition, int year_published, GenreList genreList) {
        super(title, publisher);
        this.isbn = isbn;
        this.edition = edition;
        this.year_published = year_published;
        this.genreList = genreList;
        setAuthor(author);
    }

    /**
     * Book constructor with super method and without genre list
     * @param author
     * The author of the book
     * @param title
     * The title of the book
     * @param publisher
     * The publisher of the book
     * @param isbn
     * The isbn of the book
     * @param edition
     * The edition of the book
     * @param year_published
     * The year published of the book
     */
    public Book(String author,String title, String publisher, String isbn, int edition, int year_published) {
        super(title, publisher);
        this.isbn = isbn;
        this.edition = edition;
        this.year_published = year_published;
        setAuthor(author);
    }

    /**
     * Book constructor with super method.
     * constructor without genreList and author
     * @param title
     * The title of the book
     * @param publisher
     * The publisher of the book
     * @param isbn
     * The isbn of the book
     * @param edition
     * The edition of the book
     * @param year_published
     * The year published of the book
     */
    public Book(String title, String publisher, String isbn, int edition, int year_published) {
        super(title, publisher);
        this.isbn = isbn;
        this.edition = edition;
        this.year_published = year_published;
        setAuthor("Anonymous");
    }


    /**
     * Get Isbn methon
     * @return
     * The isbn of the book
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Get edition method
     * @return
     * The edition of the book
     */
    public int getEdition() {
        return edition;
    }

    /**
     * Get year of published method
     * @return
     * The year of published of the book
     */
    public int getYear_published() {
        return year_published;
    }

    /**
     * Get author method
     * @return
     * The author of the book
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Get genre list method
     * @return
     * The genre list of the book
     */
    public GenreList getGenreList() {
        return genreList;
    }

    /**
     * Set id method
     * @param id
     * The unique identification number of book
     */
    public void setId(int id) {
        super.setId(id);
    }

    /**
     * Set genre list method
     * @param genreList
     * The genres of the book
     */
    public void setGenreList(GenreList genreList) {
        this.genreList = genreList;
    }

  /**
     * Set an author in case it is not anonymous.
     * @param author
     * The author of the book.
     */
    public void setAuthor(String author) {
        this.author = Objects.requireNonNullElse(author, "Anonymous");
    }

    /**
     * toString method of book
     * @return
     * Display al the variables with their values as a String
     */
  @Override public String toString() {
    return "Title="+getTitle()+", author="+author+", edition=" + edition + ", year published=" + year_published
        + ", isbn=" + isbn;
  }
}
