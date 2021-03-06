package client;

import model.LibraryUser;

import java.io.Closeable;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * The library user interface for library user.
 * @author Franciszek Jan Nurkiewicz
 * @version 1.0 12/04/2022
 */
public interface LibraryUserClient extends Closeable
{
  /**
   * Add library user method
   * @param libraryUser
   * The library user passed as an argument
   * @throws RemoteException
   * @throws SQLException
   */
  void addLibraryUser(LibraryUser libraryUser)throws RemoteException;

  /**
   * Remove a library user with a specific social security number
   * @param ssn
   * The social security number passed as an argument
   * @throws RemoteException
   * @throws SQLException
   */
  void removeLibraryUser(String ssn)throws RemoteException;

  /**
   * Get library user list method
   * @return
   * The library users in the list
   * @throws RemoteException
   * @throws SQLException
   */
  ArrayList<LibraryUser> getLibraryUserList() throws RemoteException;
}
