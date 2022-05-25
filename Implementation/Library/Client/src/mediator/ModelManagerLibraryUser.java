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

  public ModelManagerLibraryUser(LibraryUserClient client){
    this.client = client;
    this.support = new PropertyChangeSupport(this);
  }



  @Override public void addLibraryUser(LibraryUser libraryUser) throws RemoteException{
    client.addLibraryUser(libraryUser);
    support.firePropertyChange("addLibraryUser",null, libraryUser);
  }

  @Override public void removeLibraryUser(String ssn) throws RemoteException{
    client.removeLibraryUser(ssn);
    support.firePropertyChange("removeLibraryUser",null,ssn);
  }

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
