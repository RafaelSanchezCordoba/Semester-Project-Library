package client;

import model.Magazine;

import java.io.Closeable;
import java.rmi.RemoteException;

/**
 * The client interface for magazine.
 * @author Maria Ortiz Planchuelo.
 * @version 1.0 09/04/22.
 */
public interface MagazineClient extends Closeable
{
  void addMagazine(Magazine magazine) throws RemoteException;
  void removeMagazine(int id) throws RemoteException;


}
