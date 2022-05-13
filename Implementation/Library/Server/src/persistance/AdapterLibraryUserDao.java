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
public class AdapterLibraryUserDao implements LibraryUserStorage
{
  private LibraryUserDAO libraryUserDAO;

  public AdapterLibraryUserDao(LibraryUserDAO libraryUserDAO){
    this.libraryUserDAO = libraryUserDAO;
  }

  @Override public void addLibraryUser(LibraryUser libraryUser)
      throws RemoteException
  {
    try
    {
      this.libraryUserDAO.addLibraryUser(libraryUser);
    }catch (SQLException e){
      throw  new RemoteException(e.getMessage(),e);
    }

  }

  @Override public void removeLibraryUser(String ssn)
      throws RemoteException
  {
    try
    {
      this.libraryUserDAO.removeLibraryUser(ssn);
    }catch (SQLException e){
      throw new RemoteException(e.getMessage(),e);
    }

  }

  @Override public ArrayList<LibraryUser> getLibraryUserList()
      throws RemoteException
  {
    try
    {
      return libraryUserDAO.getLibraryUserList();
    }catch (SQLException e){
      throw  new RemoteException(e.getMessage(),e);
    }

  }
}
