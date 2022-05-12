package persistance;

import model.LibraryUser;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class LibraryUserDAOImplementation implements LibraryUserDAO
{
  private String insertLibraryUserSql = "INSERT INTO \"library\".libraryUSer(ssn,first,volume,genre,date)"
      +"VALUES(?,?,?,?,?)";
  private String removeLibraryUserSql= "";

  private LibraryUserDAOImplementation instance;

  private LibraryUserDAOImplementation() throws SQLException
  {
    DriverManager.registerDriver(new org.postgresql.Driver());
  }

  public LibraryUserDAOImplementation getInstance() throws SQLException
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

  }

  @Override public void removeLibraryUser(int ssn) throws SQLException
  {

  }

  @Override public ArrayList<LibraryUser> getLibraryUserList()
      throws SQLException
  {
    return null;
  }
}
