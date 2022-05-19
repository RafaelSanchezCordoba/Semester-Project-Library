package persistance.DAO_implementation;

import model.Book;
import model.LibraryUser;
import model.LoanBook;
import persistance.DAO.BookDAO;
import persistance.DAO.LoanBookDAO;

import java.sql.*;
import java.util.ArrayList;

public class LoanBookDAOImplementation implements LoanBookDAO
{
  private final String createLoanBookSql = "INSERT INTO \"library\".loan_book(book_id,start_of_loan,end_of_loan,library_user)"
      +"VALUES(?,?,?,?)";

  private final String getAvailableBooksSql = "SELECT * FROM \"library\".book WHERE is_available = TRUE ";

  private String getLibraryUser = "SELECT * FROM \"library\".library_user WHERE ssn = ? ";

  private String setAvailable = "UPDATE \"library\".book SET is_available = false WHERE id = ?";

  private static  LoanBookDAOImplementation instance;

  private LoanBookDAOImplementation()throws SQLException{
    DriverManager.registerDriver(new org.postgresql.Driver());
  }

  public static synchronized LoanBookDAOImplementation getInstance()
      throws SQLException
  {
    if (instance==null){
      instance= new LoanBookDAOImplementation();
    }
    return instance;
  }

  private Connection getConnection() throws SQLException
  {
    return DriverManager.getConnection("jdbc:postgresql://tai.db.elephantsql.com/naeoxool",
        "naeoxool","1eiSjWkSFVXj15hc0j47p_js1irgaDWr");
  }

  @Override public void addLoanBook(LoanBook loanBook) throws SQLException
  {
    try (Connection connection = getConnection()) {
      PreparedStatement statement = connection.prepareStatement((createLoanBookSql), PreparedStatement.RETURN_GENERATED_KEYS);
      statement.setInt(1, loanBook.getId_book());
      statement.setDate(2,loanBook.getStartDate());
      statement.setDate(3, loanBook.getEndDate());
      statement.setString(4, loanBook.getSsn());

      statement.executeUpdate();

      ResultSet keys = statement.getGeneratedKeys();
      if (keys.next()) {
        loanBook.setId(keys.getInt(1));
      } else {
        throw new SQLException("No keys generated");
      }
      PreparedStatement statementSetFalse = connection.prepareStatement(setAvailable);

      statementSetFalse.setInt(1,loanBook.getId_book());
      statementSetFalse.executeUpdate();
      statementSetFalse.close();

    }

  }

  @Override public ArrayList<Book> getAvailableBooks() throws SQLException
  {
    Connection connection = getConnection();
    try {
      connection.setAutoCommit(false);
      PreparedStatement statement = connection.prepareStatement(getAvailableBooksSql);
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

  @Override public LibraryUser getUser(String ssn) throws SQLException
  {
    try(Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(getLibraryUser);
      statement.setString(1,ssn);
      ResultSet resultSet = statement.executeQuery();


      while (resultSet.next()){
        String socialanumber = resultSet.getString("ssn");
        String password = resultSet.getString("password");
        String f_name = resultSet.getString("f_name");
        String l_name = resultSet.getString("l_name");

        LibraryUser result = new LibraryUser(socialanumber,f_name,l_name,password);

        return result;
      }
    }
    return null;
  }
}
