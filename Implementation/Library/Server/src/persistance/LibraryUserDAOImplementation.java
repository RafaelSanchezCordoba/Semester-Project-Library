package persistance;

import model.Book;
import model.LibraryUser;

import java.sql.*;
import java.util.ArrayList;

/**
 * class to access the db to store or remove data implements <code>LibraryUserDao</code>.
 * @author Alexandru Dulghier
 * @version 1.0
 */
public class LibraryUserDAOImplementation implements LibraryUserDAO
{
  private String insertLibraryUserSql = "INSERT INTO \"library\".library_user(ssn,password,f_name,l_name)"
      +"VALUES(?,?,?,?)";
  private String removeLibraryUserSql= "DELETE FROM \"library\".library_user WHERE ssn = ?";

  private String getLibraryUserList = "SELECT * FROM \"library\".library_user";

  private static LibraryUserDAOImplementation instance;

  private LibraryUserDAOImplementation() throws SQLException
  {
    DriverManager.registerDriver(new org.postgresql.Driver());
  }

  public static synchronized LibraryUserDAOImplementation getInstance() throws SQLException
  {
    if(instance ==null){
      instance = new LibraryUserDAOImplementation();
    }
    return instance;
  }
  private Connection getConnection() throws SQLException
  {
    return DriverManager.getConnection("jdbc:postgresql://tai.db.elephantsql.com/naeoxool",
        "naeoxool","1eiSjWkSFVXj15hc0j47p_js1irgaDWr");
  }

  @Override public void addLibraryUser(LibraryUser libraryUser)
      throws SQLException
  {
    try (Connection connection = getConnection()) {
      PreparedStatement statement = connection.prepareStatement((insertLibraryUserSql));
      statement.setString(1, libraryUser.getSSN());
      statement.setString(2, libraryUser.getPassword());
      statement.setString(3, libraryUser.getFirstName());
      statement.setString(4, libraryUser.getLastName());
      statement.executeUpdate();
  }
  }

  @Override public void removeLibraryUser(String ssn) throws SQLException
  {
    try(Connection connection = getConnection())
    {
      System.out.println("database" + ssn);
      PreparedStatement statement = connection.prepareStatement(removeLibraryUserSql);
      statement.setString(1, ssn);
      statement.executeUpdate();
    }
  }

  @Override public ArrayList<LibraryUser> getLibraryUserList() throws SQLException {
    try (Connection connection = getConnection()) {
      PreparedStatement statement = connection.prepareStatement(getLibraryUserList);
      ResultSet resultSet = statement.executeQuery();
      ArrayList<LibraryUser> result = new ArrayList<>();
      while (resultSet.next()) {
        String ssn = resultSet.getString("ssn");
        String password = resultSet.getString("password");
        String firstName = resultSet.getString("f_name");
        String lastName = resultSet.getString("l_name");

        LibraryUser libraryUser = new LibraryUser(ssn, firstName, lastName, password);
        result.add(libraryUser);
      }
      return result;
    }
  }
}
