package server.storage;

import model.LibraryUser;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface LibraryUserStorage
{

  void addLibraryUser(LibraryUser libraryUser) throws RemoteException;
  void removeLibraryUser(String ssn)throws RemoteException;
  ArrayList<LibraryUser> getLibraryUserList() throws RemoteException;
}
