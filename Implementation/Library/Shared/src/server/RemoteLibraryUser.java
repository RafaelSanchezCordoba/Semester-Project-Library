package server;

import model.LibraryUser;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface RemoteLibraryUser extends Remote
{
  void addLibraryUser(LibraryUser libraryUser) throws RemoteException;
  void removeLibraryUser(String ssn)throws RemoteException;
  ArrayList<LibraryUser> getLibraryUserList() throws RemoteException;
}
