package client;

import model.LibraryUser;

import java.io.Closeable;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface LibraryUserClient extends Closeable
{
  void addLibraryUser(LibraryUser libraryUser) throws RemoteException, SQLException;
  void removeLibraryUser(String ssn)throws RemoteException,SQLException;
  ArrayList<LibraryUser> getLibraryUserList() throws RemoteException,SQLException;
}
