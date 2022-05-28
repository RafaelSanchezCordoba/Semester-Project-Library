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

public class LibraryUserClientImplementation extends UnicastRemoteObject implements LibraryUserClient {

  private final RemoteLibraryUser remoteLibraryUser;

  /**
   * The constructor that create and set the Registry.
   * @param host
   * The name of the host
   * @param port
   * The port number
   * @throws IOException
   * @throws NotBoundException
   */
  public LibraryUserClientImplementation(String host,int port) throws IOException, NotBoundException {
    Registry registry = LocateRegistry.getRegistry(host,port);
    remoteLibraryUser = (RemoteLibraryUser) registry.lookup("libraryUser");
  }

  /**
   * Add library user method
   * @param libraryUser
   * The library user passed as an argument
   * @throws RemoteException
   * @throws SQLException
   */
  @Override public void addLibraryUser(LibraryUser libraryUser)
      throws RemoteException
  {
    remoteLibraryUser.addLibraryUser(libraryUser);
  }

  /**
   * Remove a library user with a specific social security number
   * @param ssn
   * The social security number passed as an argument
   * @throws RemoteException
   * @throws SQLException
   */
  @Override public void removeLibraryUser(String ssn) throws  RemoteException {
      remoteLibraryUser.removeLibraryUser(ssn);
  }

  /**
   * Get library user list method
   * @return
   * The library users in the list
   * @throws RemoteException
   * @throws SQLException
   */
  @Override public ArrayList<LibraryUser> getLibraryUserList() throws RemoteException {
    return remoteLibraryUser.getLibraryUserList();
  }

  /**
   * Close the remote object
   * @throws IOException
   */
  @Override public void close() throws IOException {
    UnicastRemoteObject.unexportObject(this,true);
  }
}
