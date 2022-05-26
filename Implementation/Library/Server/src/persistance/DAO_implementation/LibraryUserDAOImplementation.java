package persistance.DAO_implementation;

import model.LibraryUser;
import persistance.DAO.LibraryUserDAO;

import java.sql.*;
import java.util.ArrayList;

/**
 * class to access the db to store or remove data implements <code>LibraryUserDao</code>.
 * The class is opening and closing connection with database
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

  /**
   * LibraryUserDAOImplementation constructor with zero parameters
   * Driver manager inside the constructor will attempt to connect
   * to the database
   * @throws SQLException
   */
  private LibraryUserDAOImplementation() throws SQLException
  {
    DriverManager.registerDriver(new org.postgresql.Driver());
  }

  /**
   * Method returning an instance of LibraryUserDAOImplementation
   * @return
   * instance of LibraryUserDAOImplementation
   * @throws SQLException
   */
  public static synchronized LibraryUserDAOImplementation getInstance() throws SQLException
  {
    if(instance ==null){
      instance = new LibraryUserDAOImplementation();
    }
    return instance;
  }

  /**
   * getConnection method
   * @return
   * Driver manager connecting using url,user, and password
   * @throws SQLException
   */
  private Connection getConnection() throws SQLException
  {
    return DriverManager.getConnection("jdbc:postgresql://tai.db.elephantsql.com/naeoxool",
        "naeoxool","1eiSjWkSFVXj15hc0j47p_js1irgaDWr");
  }

  /**
   * Method adding a library user
   * @param libraryUser
   * Library user object
   * @throws SQLException
   */
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

  /**
   * Method removing a library user after it's SSN
   * @param ssn
   * Social security number
   * @throws SQLException
   */
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

  /**
   * Method returning library users in an ArrayList
   * @return
   * ArrayList of a library users
   * @throws SQLException
   */
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
