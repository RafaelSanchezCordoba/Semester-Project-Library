package model;

import java.io.Serializable;

/**
 * Sub-class Book that extends <code>"MultimediaItem"</code>  and implements <code>Serializable</code>.
 * @author Rafael Sánchez Córdoba.
 * @version 1.0 - 08/04/22.
 */
public class Book extends MultimediaItem implements Serializable {
    private final int isbn, edition, year_published;
    private String author;
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
    public Book(String title, String publisher, int isbn, int edition, int year_published, GenreList genreList) {
        super(title, publisher);
        this.isbn = isbn;
        this.edition = edition;
        this.year_published = year_published;
        this.author = null;
        this.genreList = genreList;
        setAuthor("Anonymous");
    }
  public Book(String author,String title, String publisher, int isbn, int edition, int year_published, GenreList genreList) {
    super(title, publisher);
    this.isbn = isbn;
    this.edition = edition;
    this.year_published = year_published;
    this.author = null;
    this.genreList = genreList;
    setAuthor("Anonymous");
  }
  public Book(String author,String title, String publisher, int isbn, int edition, int year_published) {
    super(title, publisher);
    this.isbn = isbn;
    this.edition = edition;
    this.year_published = year_published;
    this.author = null;

    setAuthor(author);
  }
  public Book(String title, String publisher, int isbn, int edition, int year_published) {
    super(title, publisher);
    this.isbn = isbn;
    this.edition = edition;
    this.year_published = year_published;
    this.author = null;

    setAuthor("Anonymous");
  }




  public int getIsbn()
  {
    return isbn;
  }

  public int getEdition()
  {
    return edition;
  }

  public int getYear_published()
  {
    return year_published;
  }

  public String getAuthor()
  {
    return author;
  }

  public GenreList getGenreList()
  {
    return genreList;
  }

  public void setId(int id) {
      super.setId(id);
  }


  /**
     * Set an author in case it is not anonymous.
     * @param author
     * The author of the book.
     */
    public void setAuthor(String author) {
        this.author = author;
    }
}
