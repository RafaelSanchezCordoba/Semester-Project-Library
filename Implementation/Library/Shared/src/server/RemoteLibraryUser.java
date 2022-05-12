package server;

import model.LibraryUser;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface RemoteLibraryUser extends Remote
{
  void addLibraryUser(LibraryUser libraryUser) throws RemoteException, SQLException;
  void removeLibraryUser(int ssn)throws RemoteException,SQLException;
  ArrayList<LibraryUser> getLibraryUserList() throws RemoteException,SQLException;
}
