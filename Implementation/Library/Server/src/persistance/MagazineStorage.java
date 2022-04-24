package persistance;

import model.Magazine;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public interface MagazineStorage
{
  void addMagazine(Magazine magazine) throws SQLException;
  void removeMagazine(int id) throws SQLException;
  ArrayList<Magazine> getMagazineList() throws SQLException;
}
