package client;

import model.Magazine;

import java.io.Closeable;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * The client interface for magazine.
 * @author Maria Ortiz Planchuelo.
 * @version 1.0 09/04/22.
 */
public interface MagazineClient extends Closeable
{
  void addMagazine(Magazine magazine) throws RemoteException, SQLException;
  void removeMagazine(int id) throws RemoteException, SQLException;
  ArrayList<Magazine> getMagazineList() throws SQLException, RemoteException;


}
