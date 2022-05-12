package persistance;
import model.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public interface MagazineDAO {
  void removeMagazine(int id) throws SQLException;
  void addMagazine(Magazine magazine) throws SQLException;
  ArrayList<Magazine> getMagazineList() throws SQLException;
}
