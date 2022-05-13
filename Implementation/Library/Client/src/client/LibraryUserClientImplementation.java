package client;

import model.LibraryUser;
import server.RemoteLibraryUser;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 * The client implementation for library user that extends <code>UnicastRemoteObject</code> and implements <code>LibraryUserClient</code>.
 * @author Alexandru Dulghier
 * @version 1.0 13/05/22.
 */

public class LibraryUserClientImplementation extends UnicastRemoteObject implements LibraryUserClient
{

  private RemoteLibraryUser remoteLibraryUser;

  public LibraryUserClientImplementation(String host,int port) throws IOException, NotBoundException
  {
    Registry registry = LocateRegistry.getRegistry(host,port);
    remoteLibraryUser = (RemoteLibraryUser) registry.lookup("libraryUser");
  }

  @Override public void addLibraryUser(LibraryUser libraryUser)
      throws SQLException, RemoteException
  {
    remoteLibraryUser.addLibraryUser(libraryUser);
  }

  @Override public void removeLibraryUser(int ssn)
      throws SQLException, RemoteException
  {
      remoteLibraryUser.removeLibraryUser(ssn);
  }

  @Override public ArrayList<LibraryUser> getLibraryUserList()
      throws RemoteException, SQLException
  {
    return remoteLibraryUser.getLibraryUserList();
  }

  @Override public void close() throws IOException
  {

  }
}
