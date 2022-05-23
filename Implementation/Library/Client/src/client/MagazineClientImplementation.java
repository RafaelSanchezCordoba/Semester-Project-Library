package client;

import model.Magazine;
import server.RemoteMagazine;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * The client implementation for magazine that extends <code>UnicastRemoteObject</code> and implements <code>MagazineClient</code>.
 * @author Maria Ortiz Planchuelo.
 * @version 1.0 09/04/22.
 */
public class MagazineClientImplementation extends UnicastRemoteObject implements MagazineClient{

  private final RemoteMagazine remoteMagazine;

  /**
   * The constructor that create and set the Registry.
   * @param host
   * The name of the host
   * @param port
   * The port number
   * @throws IOException
   * @throws NotBoundException
   */
  public MagazineClientImplementation(String  host, int port) throws IOException, NotBoundException
  {

    Registry registry = LocateRegistry.getRegistry(host, port);
    remoteMagazine = (RemoteMagazine) registry.lookup("magazine");

  }

  /**
   * Add magazine method
   * @param magazine
   * The magazine passed as an argument
   * @throws RemoteException
   * @throws SQLException
   */
  @Override public void addMagazine(Magazine magazine) throws RemoteException
  {
    try
    {
      remoteMagazine.addMagazine(magazine);
    }catch (SQLException e){
      e.printStackTrace();
    }
  }

  /**
   * Remove a magazine with a specific identification number
   * @param id
   * The unique identification number passed as an argument
   * @throws RemoteException
   * @throws SQLException
   */
  @Override public void removeMagazine(int id) throws RemoteException
  {
    try
    {
      remoteMagazine.removeMagazine(id);
    }catch (SQLException e){
      e.printStackTrace();
    }

  }

  /**
   * Get magazine list method
   * @return
   * The magazines in the list
   * @throws SQLException
   * @throws RemoteException
   */
  @Override public ArrayList<Magazine> getMagazineList()
      throws SQLException, RemoteException
  {
    return remoteMagazine.getMagazineList();
  }

  /**
   * Close the remote object
   * @throws IOException
   */
  @Override public void close() throws IOException
  {
      UnicastRemoteObject.unexportObject(this,true);
  }


}
