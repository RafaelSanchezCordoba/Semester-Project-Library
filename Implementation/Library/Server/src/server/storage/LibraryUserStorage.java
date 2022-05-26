package server.storage;

import model.LibraryUser;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * The LibraryUserStorage interface
 * This interface is implemented by AdapterLibraryUserDAO class
 */
public interface LibraryUserStorage
{

  /**
   * Add a library user
   * @param libraryUser
   * Library user object
   * @throws RemoteException
   */
  void addLibraryUser(LibraryUser libraryUser) throws RemoteException;

  /**
   * Remove a library user by the SSN
   * @param ssn
   * Social Security Number
   * @throws RemoteException
   */
  void removeLibraryUser(String ssn)throws RemoteException;

  /**
   * Return a list with all the library users added
   * @return
   * library users list
   * @throws RemoteException
   */
  ArrayList<LibraryUser> getLibraryUserList() throws RemoteException;
}
