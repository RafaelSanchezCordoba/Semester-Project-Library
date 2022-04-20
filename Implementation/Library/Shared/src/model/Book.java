package model;

import java.io.Serializable;

/**
 * Sub-class Book that extends <code>"MultimediaItem"</code>  and implements <code>Serializable</code>.
 * @author Rafael Sánchez Córdoba.
 * @version 1.0 - 08/04/22.
 */
public class Book extends MultimediaItem implements Serializable {
    private final String isbn;
    private final int edition, year_published;
    private Author author;
    private GenreList genreList;

    /**
     * Book constructor with super method.
     * The author is set to null because it could be an anonymous writer.
     * @param id
     * The unique identification number of book.
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
    public Book(int id, String title, String publisher, String isbn, int edition, int year_published, GenreList genreList) {
        super(id, title, publisher);
        this.isbn = isbn;
        this.edition = edition;
        this.year_published = year_published;
        this.author = null;
        this.genreList = genreList;
    }

  public Book(int id,String title,String publisher)
  {
    super(id,title,publisher);
    isbn= null;
    edition = 0;
    year_published = 0;
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

  public Author getAuthor()
  {
    return author;
  }

  public GenreList getGenreList()
  {
    return genreList;
  }

  public Book(){
      isbn= null;
      edition = 0;
      year_published = 0;

  }
  /**
     * Set an author in case it is not anonymous.
     * @param author
     * The author of the book.
     */
    public void setAuthor(Author author) {
        this.author = author;
    }
}
