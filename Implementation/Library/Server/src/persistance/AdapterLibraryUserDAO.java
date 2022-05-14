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

  @Override public void addLibraryUser(LibraryUser libraryUser) throws RemoteException, SQLException {
    libraryUserDAO.addLibraryUser(libraryUser);
  }

  @Override public void removeLibraryUser(String ssn) throws SQLException {
    libraryUserDAO.removeLibraryUser(ssn);
  }

  @Override public ArrayList<LibraryUser> getLibraryUserList() throws RemoteException, SQLException {
    return libraryUserDAO.getLibraryUserList();
  }
}
