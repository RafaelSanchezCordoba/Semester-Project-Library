package mediator;

import client.LibraryUserClient;
import model.LibraryUser;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public class ModelManagerLibraryUser implements ModelLibraryUser
{
  private final LibraryUserClient client;
  private final PropertyChangeSupport support;

  /**
   * Public constructor that set the client and a property change support
   * @param client
   * The library user client
   */
  public ModelManagerLibraryUser(LibraryUserClient client){
    this.client = client;
    this.support = new PropertyChangeSupport(this);
  }

  /**
   * Add library user method
   * @param libraryUser
   * The library user passed as an argument
   */
  @Override public void addLibraryUser(LibraryUser libraryUser)
      throws RemoteException
  {
    client.addLibraryUser(libraryUser);
    support.firePropertyChange("addLibraryUser",null, libraryUser);
  }

  /**
   * Remove a library user with a specific social security number
   * @param ssn
   * The social security number passed as an argument
   * @throws RemoteException
   */
  @Override public void removeLibraryUser(String ssn) throws RemoteException{
    client.removeLibraryUser(ssn);
    support.firePropertyChange("removeLibraryUser",null,ssn);
  }

  /**
   * Get library user list method
   * @return
   * The library users in the list
   * @throws RemoteException
   */
  @Override
  public ArrayList<LibraryUser> getLibraryUserList() throws RemoteException{
    return client.getLibraryUserList();
  }

  /**
   * Add property change listener, just with the listener
   * @param listener
   * The listener
   */
  @Override public void addPropertyChangeListener(PropertyChangeListener listener){
    support.addPropertyChangeListener(listener);
  }

  /**
   * Add property change listener with a name and the listener
   * @param name
   * The name
   * @param listener
   * The listener
   */
  @Override public void addPropertyChangeListener(String name, PropertyChangeListener listener){
    support.addPropertyChangeListener(name, listener);
  }

  /**
   * Remove property change listener, just with the listener
   * @param listener
   * The listener
   */
  @Override public void removePropertyChangeListener(PropertyChangeListener listener){
    support.removePropertyChangeListener(listener);
  }

  /**
   * Remove property change listener with a name and the listener
   * @param name
   * The name
   * @param listener
   * The listener
   */
  @Override public void removePropertyChangeListener(String name, PropertyChangeListener listener){
    support.removePropertyChangeListener(name, listener);
  }

}
