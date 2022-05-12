package mediator;

import client.LibrarianClient;
import model.Librarian;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * The model manager for Librarian.
 * @author Rafael Sánchez Córdoba.
 * @version 1.2 09/05/22
 */

public class ModelManagerLibrarian implements ModelLibrarian
{
  private final LibrarianClient client;
  private final PropertyChangeSupport support;

  /**
   * Public constructor that set the client and a property change support
   * @param client
   * The LibrarianClient
   */
  public ModelManagerLibrarian(LibrarianClient client){
    this.client = client;
    support = new PropertyChangeSupport(this);
  }

  /**
   * Add a Librarian
   * @param librarian
   * Librarian object
   * @throws RemoteException
   */
  public void addLibrarian(Librarian librarian) throws RemoteException, SQLException {
    client.addLibrarian(librarian);
    support.firePropertyChange("newLibrarian", null, librarian);
  }

  /**
   * Remove a Librarian by the SSN
   * @param SSN
   * The Social Security Number
   * @throws RemoteException
   */
<<<<<<< Updated upstream
  public void removeLibrarian(int SSN) throws RemoteException, SQLException {
=======
<<<<<<< HEAD
  public void removeLibrarian(String SSN) throws RemoteException, SQLException {
=======
  public void removeLibrarian(int SSN) throws RemoteException, SQLException {
>>>>>>> 7e718619a38eaa66df02ddfe9864aa4893dd4e7c
>>>>>>> Stashed changes
    client.removeLibrarian(SSN);
    support.firePropertyChange("removeLibrarian", null , SSN);
  }

  /**
   * Return a list with all the librarians added
   * @return
   * Librarian List
   * @throws RemoteException
   * @throws SQLException
   */
  public ArrayList<Librarian> getLibrarianList() throws RemoteException, SQLException {
     return  client.getLibrarianList();
  }

  @Override
  public void addPropertyChangeListener(PropertyChangeListener listener) {
    support.addPropertyChangeListener(listener);
  }

  @Override
  public void addPropertyChangeListener(String name, PropertyChangeListener listener) {
    support.addPropertyChangeListener(name, listener);
  }

  @Override
  public void removePropertyChangeListener(PropertyChangeListener listener) {
    support.removePropertyChangeListener(listener);
  }

  @Override
  public void removePropertyChangeListener(String name, PropertyChangeListener listener) {
    support.removePropertyChangeListener(name, listener);
  }
}
