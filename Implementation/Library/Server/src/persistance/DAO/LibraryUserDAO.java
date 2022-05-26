package persistance.DAO;

import model.LibraryUser;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * The LibraryUserDAO interface
 * This interface is implemented by LibraryUserDAOImplementation
 */
public interface LibraryUserDAO
{
  /**
   * Method adding a library user
   * @param libraryUser
   * Library user object
   * @throws SQLException
   */
  void addLibraryUser(LibraryUser libraryUser) throws SQLException;

  /**
   * Remove a library user by the ssn
   * @param ssn
   * Social security number
   * @throws SQLException
   */
  void removeLibraryUser(String ssn)throws SQLException;

  /**
   * Return a list with all the library users added
   * @return
   * Library users list
   * @throws SQLException
   */
  ArrayList<LibraryUser> getLibraryUserList() throws SQLException;
}
