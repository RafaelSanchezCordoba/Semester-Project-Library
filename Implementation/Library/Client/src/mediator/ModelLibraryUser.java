package mediator;

import model.LibraryUser;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * The model interface for book
 * @author Franciszek Jan Nurkiewicz
 * @version 12.05.2022
 */
public interface ModelLibraryUser extends PropertyChangeSubject {
  /**
   * Add library user method
   * @param libraryUser
   * The library user passed as an argument
   * @throws RemoteException
   * @throws SQLException
   */
  void addLibraryUser(LibraryUser libraryUser) throws RemoteException;

  /**
   * Remove a library user with a specific social security number
   * @param ssn
   * The social security number passed as an argument
   * @throws RemoteException
   * @throws SQLException
   */
  void removeLibraryUser(String ssn) throws RemoteException;

  /**
   * Get library user list method
   * @return
   * The library users in the list
   * @throws RemoteException
   * @throws SQLException
   */
  ArrayList<LibraryUser> getLibraryUserList() throws RemoteException;

}
