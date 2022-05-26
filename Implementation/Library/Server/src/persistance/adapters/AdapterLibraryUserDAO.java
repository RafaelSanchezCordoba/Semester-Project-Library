package persistance.adapters;

import model.LibraryUser;
import persistance.DAO.LibraryUserDAO;
import server.storage.LibraryUserStorage;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * adapter pattern for Library user DAO implements <code>LibraryUserStorage</code>
 * Public class AdapterLibraryUserDAO for DAO implementing LibraryUserStorage interface
 * This adapter's purpose is to minimize the direct dependency between application code
 * and data access code.
 * @author Alexandru Dulghier
 * @version 1.0
 */
public class AdapterLibraryUserDAO implements LibraryUserStorage
{
  private LibraryUserDAO libraryUserDAO;

  /**
   * AdapterLibraryUserDAO one parameter constructor
   * @param libraryUserDAO
   * Parameter from interface LibraryUserDAO
   */
  public AdapterLibraryUserDAO(LibraryUserDAO libraryUserDAO){
    this.libraryUserDAO = libraryUserDAO;
  }

  /**
   * Method adding a library user.
   * @param libraryUser
   * LibraryUser
   * @throws RemoteException
   */
  @Override public void addLibraryUser(LibraryUser libraryUser) throws RemoteException {

    try {
      libraryUserDAO.addLibraryUser(libraryUser);
    } catch (SQLException e) {
      e.printStackTrace();
      throw new RemoteException(e.getMessage());
    }
  }

  /**
   * Method removing a library user after it's SSN
   * @param ssn
   * Social security number
   * @throws RemoteException
   */
  @Override public void removeLibraryUser(String ssn) throws RemoteException{
    try {
      libraryUserDAO.removeLibraryUser(ssn);
    } catch (SQLException e) {
      throw new  RemoteException(e.getMessage(), e);
    }
  }

  /**
   * Method returning list of library users as ArrayList
   * @return
   * List of library users as ArrayList
   * @throws RemoteException
   */
  @Override public ArrayList<LibraryUser> getLibraryUserList() throws RemoteException {
    try {
      return libraryUserDAO.getLibraryUserList();
    } catch (SQLException e) {
      e.printStackTrace();
      throw new RemoteException(e.getMessage());
    }
  }
}
