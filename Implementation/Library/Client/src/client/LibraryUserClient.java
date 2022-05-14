package client;

import model.LibraryUser;

import java.io.Closeable;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * The library user interface for library user.
 * @author Franciszek Jan Nurkiewicz
 * @version 1.0 12/04/2022
 */
public interface LibraryUserClient extends Closeable
{
  void addLibraryUser(LibraryUser libraryUser) throws RemoteException, SQLException;
  void removeLibraryUser(String ssn)throws RemoteException,SQLException;
  ArrayList<LibraryUser> getLibraryUserList() throws RemoteException,SQLException;
}
