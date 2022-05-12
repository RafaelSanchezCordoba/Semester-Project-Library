package persistance;

import model.LibraryUser;
import server.LibraryUserStorage;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class LibraryUserStorageTest implements LibraryUserStorage
{
  private ArrayList<LibraryUser> libraryUsers;
  public static LibraryUserStorageTest instance;

  private LibraryUserStorageTest(){
    libraryUsers = new ArrayList<LibraryUser>();
  }
  public static synchronized LibraryUserStorageTest getInstance(){
    if (instance == null){
      instance = new LibraryUserStorageTest();
    }
    return instance;
  }

  @Override public void addLibraryUser(LibraryUser libraryUser)
      throws RemoteException, SQLException
  {
    libraryUsers.add(libraryUser);
  }

  @Override public void removeLibraryUser(int ssn)
      throws RemoteException, SQLException
  {
    for (int i = 0; i<libraryUsers.size();i++){
      if (ssn == libraryUsers.get(i).getSsn()){
        libraryUsers.remove(i);
      }
    }
  }

  @Override public ArrayList<LibraryUser> getLibraryUserList()
      throws RemoteException, SQLException
  {
    return libraryUsers;
  }


}
