package persistance.DAO_implementation;

import model.Book;
import model.Genre;
import model.GenreList;
import persistance.DAO.BookDAO;

import java.sql.*;
import java.util.ArrayList;

/**
 * Public class BookDAOImplementation implementing interface BookDAO
 * This class is opening and closing connection with database.
 */
public class BookDAOImplementation implements BookDAO
{

  //Pre-made sql to use in the methods
  private String insertBookSql = "INSERT INTO \"library\".book(isbn,publisher,title,year_published,author,edition)"
          + "VALUES( ?,?,?,?,?,?)";

  private String insertGenreBookSql = "INSERT INTO \"library\".book_genre(book_id, genre_id)" + "VALUES(?,?)";
  private String getGenreListSql= "SELECT * FROM \"library\".genre ORDER BY id DESC";

  private String removeBookSql = "DELETE FROM \"library\".book  WHERE id=? and is_available = true ";

  private String getBookListSql = "SELECT * FROM \"library\".book ORDER BY id DESC";

  private static BookDAOImplementation instance;

  /**
   * BookDAOImplementation constructor with zero parameters
   * Driver manager inside the constructor will attempt to
   * connect to the database.
   * @throws SQLException
   */
  private BookDAOImplementation() throws SQLException {
    DriverManager.registerDriver(new org.postgresql.Driver());
  }

  /**
   * GetInstance method
   * @return
   * instance of BookDAOImplementation
   * @throws SQLException
   */
  public static synchronized BookDAOImplementation getInstance() throws SQLException {
    if (instance == null) {
      instance = new BookDAOImplementation();
    }
    return instance;
  }

  /**
   * getConnection method
   * @return
   * Driver manager connecting using url,user and password.
   * @throws SQLException
   */
  private Connection getConnection() throws SQLException {
    return DriverManager.getConnection("jdbc:postgresql://tai.db.elephantsql.com/naeoxool",
            "naeoxool", "1eiSjWkSFVXj15hc0j47p_js1irgaDWr");
  }

  /**
   * Method removing a book after it's id
   * @param id
   * Id of the book
   * @throws SQLException
   * The method will throw and SQL exception if affected database row is 0
   * The message of the exception is "no delete because the book is currently lent"
   */
  @Override
  public void removeBook(int id) throws SQLException {
    try (Connection connection = getConnection()) {
      PreparedStatement statement = connection.prepareStatement(removeBookSql);
      statement.setInt(1, id);
      int rowAffected= statement.executeUpdate();

     if (rowAffected == 0){
       throw new SQLException("no delete because the book is currently lent");
     }


    }
  }

  /**
   * Method adding a book
   * @param book
   * Book
   * @throws SQLException
   * The method will throw an exception with message "No keys generated"
   */
  @Override
  public void addBook(Book book) throws SQLException {
    try (Connection connection = getConnection()) {
      PreparedStatement statement = connection.prepareStatement((insertBookSql), PreparedStatement.RETURN_GENERATED_KEYS);
      statement.setString(1, book.getIsbn());
      statement.setString(2, book.getPublisher());
      statement.setString(3, book.getTitle());
      statement.setInt(4, book.getYear_published());
      statement.setString(5, book.getAuthor());
      statement.setInt(6, book.getEdition());

      statement.executeUpdate();

      ResultSet keys = statement.getGeneratedKeys();
      if (keys.next()) {
        book.setId(keys.getInt(1));
      } else {
        throw new SQLException("No keys generated");
      }

      PreparedStatement statementGenre = connection.prepareStatement((insertGenreBookSql));
      for (int i = 0; i < book.getGenreList().getSize(); i++)
      {
        statementGenre.setInt(1,book.getId());
        statementGenre.setInt(2, book.getGenreList().getGenre(i).getId());
        statementGenre.executeUpdate();
      }
    }
  }

  /**
   * Method returning list of the books as an ArrayList
   * @return
   * ArrayList of the books
   * @throws SQLException
   */
  @Override
  public ArrayList<Book> getBookList() throws SQLException {
    Connection connection = getConnection();
    try {
      connection.setAutoCommit(false);
      PreparedStatement statement = connection.prepareStatement(getBookListSql);
      ResultSet resultSet = statement.executeQuery();
      ArrayList<Book> result = new ArrayList<>();
      while (resultSet.next()) {
        int id = resultSet.getInt("id");
        String author =  resultSet.getString("author");
        String title = resultSet.getString("title");
        String publisher = resultSet.getString("publisher");
        String Isbn = resultSet.getString("isbn");
        int Edition = resultSet.getInt("edition");
        int Year_Published = resultSet.getInt("year_published");
        Book book = new Book(author,title, publisher, Isbn, Edition, Year_Published);
        book.setId(id);
        result.add(book);
      }
      connection.commit();
      return result;
    }catch (SQLException e){
      connection.rollback();
      throw e;
    }finally
    {
      connection.close();
    }
  }

  /**
   * Method returning list of genre as
   * an ArrayList from GenreList class
   * @return
   * ArrayList of genres from Genre list class
   * @throws SQLException
   */
  public GenreList getGenreList() throws SQLException {
    try (Connection connection = getConnection()) {
      PreparedStatement statement = connection.prepareStatement(getGenreListSql);
      ResultSet resultSet = statement.executeQuery();
      ArrayList<Genre> result = new ArrayList<Genre>();
      while (resultSet.next()) {
        int id = resultSet.getInt("id");
        String genre_name =  resultSet.getString("genre");

        Genre genre = new Genre(genre_name);
        genre.setId(id);
        result.add(genre);
      }
      GenreList resultList= new GenreList();
      resultList.addAll(result);
      return resultList;
    }
  }
  }

