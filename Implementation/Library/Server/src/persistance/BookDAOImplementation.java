package persistance;

import model.Book;

import java.sql.*;
import java.util.ArrayList;

public class BookDAOImplementation implements BookDAO {

  private String driver, url, user, password;

  private Connection connection;
  private PreparedStatement insBookStatement;
  private PreparedStatement remBookStatement;


  //Pre-made sql to use in the methods
  private String insertBookSql = "INSERT INTO \"library\".book(id,isbn,publisher,title,year_published,author,edition,librarian_ssn)"
          + "VALUES( ?,?,?,?,?,?,?,?)";
  private String removeBookSql = "DELETE FROM \"library\".book "
          + "WHERE id=?";

  private String getBookListSql = "SELECT * FROM \"library\".book ORDER BY id DESC";

  private static BookDAOImplementation instance;

  private BookDAOImplementation() throws SQLException {
    DriverManager.registerDriver(new org.postgresql.Driver());
  }

  public static synchronized BookDAOImplementation getInstance() throws SQLException {
    if (instance == null) {
      instance = new BookDAOImplementation();
    }
    return instance;
  }

  private Connection getConnection() throws SQLException {
    return DriverManager.getConnection("jdbc:postgresql://tai.db.elephantsql.com/naeoxool",
            "naeoxool", "1eiSjWkSFVXj15hc0j47p_js1irgaDWr");
  }

  @Override
  public void removeBook(int id) throws SQLException {
    try (Connection connection = getConnection()) {
      PreparedStatement statement = connection.prepareStatement(removeBookSql);
      statement.setInt(1, id);
      statement.executeUpdate();
    }
  }

  @Override
  public void addBook(Book book) throws SQLException {
    try (Connection connection = getConnection()) {
      PreparedStatement statement = connection.prepareStatement((insertBookSql), PreparedStatement.RETURN_GENERATED_KEYS);
      statement.setString(1, book.getTitle());
      statement.setString(2, book.getPublisher());
      statement.setInt(3, book.getIsbn());
      statement.setInt(4, book.getEdition());
      statement.setInt(5, book.getYear_published());
      //statement.setGenre
      statement.executeUpdate();

      ResultSet keys = statement.getGeneratedKeys();
      if (keys.next()) {
        book.setId(keys.getInt(1));
      } else {
        throw new SQLException("No keys generated");
      }
    }
  }

  @Override
  public ArrayList<Book> getBookList() throws SQLException {
    try (Connection connection = getConnection()) {
      PreparedStatement statement = connection.prepareStatement(getBookListSql);
      ResultSet resultSet = statement.executeQuery();
      ArrayList<Book> result = new ArrayList<Book>();
      while (resultSet.next()) {
        int id = resultSet.getInt(1);
        String title = resultSet.getString(2);
        String publisher = resultSet.getString(3);
        int Isbn = resultSet.getInt(4);
        int Edition = resultSet.getInt(5);
        int Year_Published = resultSet.getInt(6);
        Book book = new Book(title, publisher, Isbn, Edition, Year_Published);
        result.add(book);
      }
      return result;
    }
  }
}
