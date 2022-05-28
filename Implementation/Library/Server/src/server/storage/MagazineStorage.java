package server.storage;

import model.Magazine;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Public MagazineStorage interface that is
 * implemented by AdapterMagazineDAO class
 * The purpose of this class it to provide abstract
 * interface with all methods for class AdapterMagazineDAO
 */
public interface MagazineStorage
{
  /**
   * Method adding a magazine
   * @param magazine
   * Magazine
   * @throws SQLException
   * SQL exception with the message "No keys generated" can
   * be caught from MagazineDAOImplementation
   */
  void addMagazine(Magazine magazine) throws  RemoteException;

  /**
   * Method removing magazine after it's id
   * @param id
   * Id of the magazine
   * @throws SQLException
   * SQL exception with the message "magazine is being lent"
   * can be caught from MagazineDAOImplementation
   */
  void removeMagazine(int id) throws RemoteException;

  /**
   * Method returning list of the magazine as ArrayList
   * @return
   * List of the magazine as ArrayList
   * @throws SQLException
   */
  ArrayList<Magazine> getMagazineList() throws  RemoteException;
}
