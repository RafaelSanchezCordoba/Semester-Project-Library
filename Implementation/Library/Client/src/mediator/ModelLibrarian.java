package mediator;

import model.Librarian;

import java.awt.*;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * The model interface for librarian.
 * @author Rafael Sánchez Córdoba.
 * @version 1.0 09/05/22
 */
public interface ModelLibrarian extends PropertyChangeSubject {
  /**
   * Add a Librarian
   * @param librarian
   * Librarian object
   * @throws RemoteException
   */
  void addLibrarian(Librarian librarian) throws RemoteException, SQLException;

  /**
   * Remove a Librarian by the SSN
   * @param SSN
   * The Social Security Number
   * @throws RemoteException
   */
<<<<<<< Updated upstream
  void removeLibrarian(int SSN) throws RemoteException, SQLException;
=======
<<<<<<< HEAD
  void removeLibrarian(String SSN) throws RemoteException, SQLException;
=======
  void removeLibrarian(int SSN) throws RemoteException, SQLException;
>>>>>>> 7e718619a38eaa66df02ddfe9864aa4893dd4e7c
>>>>>>> Stashed changes

  /**
   * Return a list with all the librarians added
   * @return
   * Librarian List
   * @throws RemoteException
   * @throws SQLException
   */
  ArrayList<Librarian> getLibrarianList() throws RemoteException, SQLException;
}
