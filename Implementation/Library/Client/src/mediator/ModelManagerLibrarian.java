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
 * @author Rafael Sanchez Cordoba.
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
  public void addLibrarian(Librarian librarian) throws RemoteException {
    client.addLibrarian(librarian);
    support.firePropertyChange("newLibrarian", null, librarian);
  }

  /**
   * Remove a Librarian by the SSN
   * @param SSN
   * The Social Security Number
   * @throws RemoteException
   */
  public void removeLibrarian(String SSN) throws RemoteException {
    client.removeLibrarian(SSN);
    support.firePropertyChange("removeLibrarian", null , SSN);
  }

  /**
   * Return a list with all the librarians added
   * @return
   * Librarian List
   * @throws RemoteException
   */
  public ArrayList<Librarian> getLibrarianList() throws RemoteException {
     return  client.getLibrarianList();
  }

  /**
   * Add property change listener, just with the listener
   * @param listener
   * The listener
   */
  @Override
  public void addPropertyChangeListener(PropertyChangeListener listener) {
    support.addPropertyChangeListener(listener);
  }

  /**
   * Add property change listener with a name and the listener
   * @param name
   * The name
   * @param listener
   * The listener
   */
  @Override
  public void addPropertyChangeListener(String name, PropertyChangeListener listener) {
    support.addPropertyChangeListener(name, listener);
  }

  /**
   * Remove property change listener, just with the listener
   * @param listener
   * The listener
   */
  @Override
  public void removePropertyChangeListener(PropertyChangeListener listener) {
    support.removePropertyChangeListener(listener);
  }

  /**
   * Remove property change listener with a name and the listener
   * @param name
   * The name
   * @param listener
   * The listener
   */
  @Override
  public void removePropertyChangeListener(String name, PropertyChangeListener listener) {
    support.removePropertyChangeListener(name, listener);
  }
}
