package persistance;

import model.Magazine;
import server.MagazineStorage;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdapterMagazine implements MagazineStorage
{
  private MagazineDAO magazineDAO;

  public AdapterMagazine(MagazineDAO magazineDAO)
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
