package model;

import java.io.Serializable;

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

    //constructor without author
    public Book(String title, String publisher, String isbn, int edition, int year_published, GenreList genreList) {
        super(title, publisher);
        this.isbn = isbn;
        this.edition = edition;
        this.year_published = year_published;
        this.genreList = genreList;
        setAuthor("Anonymous");
    }

    //complete constructor
  public Book(String author,String title, String publisher, String isbn, int edition, int year_published, GenreList genreList) {
    super(title, publisher);
    this.isbn = isbn;
    this.edition = edition;
    this.year_published = year_published;
    this.genreList = genreList;
    setAuthor(author);
  }

  //constructor without genreList
  public Book(String author,String title, String publisher, String isbn, int edition, int year_published) {
    super(title, publisher);
    this.isbn = isbn;
    this.edition = edition;
    this.year_published = year_published;
    setAuthor(author);
  }

  //constructor without genreList and author
  public Book(String title, String publisher, String isbn, int edition, int year_published) {
    super(title, publisher);
    this.isbn = isbn;
    this.edition = edition;
    this.year_published = year_published;
    setAuthor("Anonymous");
  }




  public String getIsbn()
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

  @Override public String toString()
  {
    return "Book{" + "edition=" + edition + ", year_published=" + year_published
        + ", isbn='" + isbn + '\'' + ", author='" + author + '\''
        + ", genreList=" + genreList + '}';
  }
}
