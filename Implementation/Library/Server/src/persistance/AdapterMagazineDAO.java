package persistance;

import model.Magazine;
import persistance.MagazineDAO;
import server.storage.MagazineStorage;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdapterMagazineDAO implements MagazineStorage
{
  private MagazineDAO magazineDAO;

  public AdapterMagazineDAO(MagazineDAO magazineDAO)
  {
    this.magazineDAO = magazineDAO;
  }

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
