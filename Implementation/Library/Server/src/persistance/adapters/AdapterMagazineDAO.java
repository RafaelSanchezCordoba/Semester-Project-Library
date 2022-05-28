package persistance.adapters;

import model.Magazine;
import persistance.DAO.MagazineDAO;
import server.storage.MagazineStorage;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Public class AdapterMagazine for DAO implementing MagazineStorage interface
 * This adapter's purpose is to minimize the direct dependency between
 * application code and data access code.
 */
public class AdapterMagazineDAO implements MagazineStorage
{
  private MagazineDAO magazineDAO;

  /**
   * AdapterMagazineDAO and parameter constructor
   * @param magazineDAO
   * Parameter from interface MagazineDOA
   */
  public AdapterMagazineDAO(MagazineDAO magazineDAO)
  {
    this.magazineDAO = magazineDAO;
  }

  /**
   * Method removing a magazine after it's id
   * @param id
   * Id of the magazine
   * @throws RemoteException
   * SQL exception with the message "magazine is being lent"
   * can be caught from MagazineDAOImplementation
   */
  @Override public void removeMagazine(int id) throws RemoteException
  {
    try
    {
      magazineDAO.removeMagazine(id);
    }
    catch (SQLException e)
    {
      throw new RemoteException(e.getMessage(), e);
    }
  }

  /**
   * Method returning list of the magazines as ArrayList
   * @return
   * ArrayList of magazines
   * @throws RemoteException
   */
  @Override public ArrayList<Magazine> getMagazineList() throws RemoteException
  {
    try
    {
      return magazineDAO.getMagazineList();
    }
    catch (SQLException e)
    {
      throw new RemoteException(e.getMessage(), e);
    }
  }

  /**
   * Method adding a magazine
   * @param magazine
   * Magazine
   * @throws RemoteException
   * SQL exception with the message "No keys generated" can
   * be caught from MagazineDAOImplementation
   */
  @Override public void addMagazine(Magazine magazine) throws RemoteException
  {
    try
    {
      magazineDAO.addMagazine(magazine);
    }
    catch (SQLException e)
    {
      throw new RemoteException(e.getMessage(), e);
    }
  }




}
