package persistance;

import model.Magazine;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class AdapterMagazine implements MagazineStorage {
  private MagazineDAO magazineDAO;

  public AdapterMagazine() throws SQLException
  {
    this.magazineDAO = MagazineDAOImplementation.getInstance();
  }

  @Override public void removeMagazine(int id) throws SQLException
  {
    magazineDAO.removeMagazine(id);
  }

  @Override public ArrayList<Magazine> getMagazineList() throws SQLException
  {
    return magazineDAO.getMagazineList();
  }

  @Override public void addMagazine(Magazine magazine) throws SQLException
  {
    magazineDAO.addMagazine(magazine);
  }




}
