package server;

import model.LibraryUser;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface LibraryUserStorage
{

  void addLibraryUser(LibraryUser libraryUser) throws RemoteException,
      SQLException;
  void removeLibraryUser(String ssn)throws RemoteException,SQLException;
  ArrayList<LibraryUser> getLibraryUserList() throws RemoteException,SQLException;
}
