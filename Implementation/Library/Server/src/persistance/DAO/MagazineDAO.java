package persistance.DAO;
import model.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Public MagazineDAO interface that is implemented by
 * MagazineDAOImplementation class
 * The purpose of this class is to provide abstract
 * interface for the database.
 */
public interface MagazineDAO {

  /**
   * Method removing magazine after it's id
   * @param id
   * Id of the magazine
   * @throws SQLException
   * SQL exception with the message "magazine is being lent"
   * can be caught from MagazineDAOImplementation
   */
  void removeMagazine(int id) throws SQLException;

  /**
   * Method adding a magazine
   * @param magazine
   * Magazine
   * @throws SQLException
   * SQL exception with the message "No keys generated" can
   * be caught from MagazineDAOImplementation
   */
  void addMagazine(Magazine magazine) throws SQLException;

  /**
   * Method returning list of the magazine as ArrayList
   * @return
   * List of the magazine as ArrayList
   * @throws SQLException
   */
  ArrayList<Magazine> getMagazineList() throws SQLException;
}
