package mediator;

import model.Librarian;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * The model interface for librarian.
 * @author Rafael Sanchez Cordoba.
 * @version 1.0 09/05/22
 */
public interface ModelLibrarian extends PropertyChangeSubject {
  /**
   * Add a Librarian
   * @param librarian
   * Librarian object
   * @throws RemoteException
   */
  void addLibrarian(Librarian librarian) throws RemoteException;

  /**
   * Remove a Librarian by the SSN
   * @param SSN
   * The Social Security Number
   * @throws RemoteException
   */
  void removeLibrarian(String SSN) throws RemoteException;

  /**
   * Return a list with all the librarians added
   * @return
   * Librarian List
   * @throws RemoteException
   */
  ArrayList<Librarian> getLibrarianList() throws RemoteException;
}
