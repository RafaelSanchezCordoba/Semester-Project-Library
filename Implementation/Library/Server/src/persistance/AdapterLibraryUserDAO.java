package persistance;

import model.LibraryUser;
import server.LibraryUserStorage;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * adapter pattern for Library user DAO implements <code>LibraryUserStorage</code>
 * @author Alexandru Dulghier
 * @version 1.0
 */
public class AdapterLibraryUserDAO implements LibraryUserStorage
{
  private LibraryUserDAO libraryUserDAO;

  public AdapterLibraryUserDAO(LibraryUserDAO libraryUserDAO){
    this.libraryUserDAO = libraryUserDAO;
  }

  @Override public void addLibraryUser(LibraryUser libraryUser) throws RemoteException {

    try {
      libraryUserDAO.addLibraryUser(libraryUser);
    } catch (SQLException e) {
      e.printStackTrace();
      throw new RemoteException(e.getMessage());
    }
  }

  @Override public void removeLibraryUser(String ssn) {
    try {
      libraryUserDAO.removeLibraryUser(ssn);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override public ArrayList<LibraryUser> getLibraryUserList() throws RemoteException {
    try {
      return libraryUserDAO.getLibraryUserList();
    } catch (SQLException e) {
      e.printStackTrace();
      throw new RemoteException(e.getMessage());
    }
  }
}
