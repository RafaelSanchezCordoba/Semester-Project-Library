package persistance;

import model.LibraryUser;
import server.LibraryUserStorage;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * class to be used to test the funcionality of the system in absence of a db connection implements <code>LibraryUserStorage</code>.
 * @author Alexandru Dulghier
 * @version 1.0 13/05/2022
 */
public class LibraryUserStorageTest implements LibraryUserStorage
{
  private ArrayList<LibraryUser> libraryUsers;
  public static LibraryUserStorageTest instance;

  /**
   * private constructor
   */
  private LibraryUserStorageTest(){
    libraryUsers = new ArrayList<LibraryUser>();
  }

  /**
   * singleton pattern to avoid having multiple instances of the storage
   * @return ArrayList
   */
  public static synchronized LibraryUserStorageTest getInstance(){
    if (instance == null){
      instance = new LibraryUserStorageTest();
    }
    return instance;
  }

  /**
   * method to add a library user to the storage
   * @param libraryUser
   * @throws RemoteException
   * @throws SQLException
   */
  @Override public void addLibraryUser(LibraryUser libraryUser)
      throws RemoteException, SQLException
  {
    libraryUsers.add(libraryUser);
  }

  /**
   * method to remove a library user from the storage by using it's ssn
   * @param ssn
   * @throws RemoteException
   * @throws SQLException
   */
  @Override
  public void removeLibraryUser(String ssn)
      throws RemoteException, SQLException
  {
    for (int i = 0; i<libraryUsers.size();i++){
      if (ssn == libraryUsers.get(i).getSSN()){
        libraryUsers.remove(i);
      }
    }
  }

  /**
   * method that returns the list of the library users
   * @return ArrayList
   * @throws RemoteException
   * @throws SQLException
   */
  @Override public ArrayList<LibraryUser> getLibraryUserList()
      throws RemoteException, SQLException
  {
    return libraryUsers;
  }


}
