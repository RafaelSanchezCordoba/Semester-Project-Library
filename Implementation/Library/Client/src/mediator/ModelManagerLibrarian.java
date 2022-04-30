package mediator;

import client.LibrarianClient;
import model.Librarian;

import javax.print.DocFlavor;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * The model manager for Librarian.
 * @author Alexandru Dulghier.
 * @version 1.0 27/04/22
 */

public class ModelManagerLibrarian implements ModelLibrarian
{
  private final LibrarianClient client;
  private final PropertyChangeSupport support;

  public ModelManagerLibrarian(LibrarianClient client){
    this.client = client;
    support = new PropertyChangeSupport(this);
  }

  public void addLibrarian(Librarian librarian) throws RemoteException
  {
    client.addLibrarian(librarian);
  }

  public void removeLibrarian(Librarian librarian) throws RemoteException{
    client.removeLibrarian(librarian);
  }

  public ArrayList<Librarian> getLibrarianList() throws RemoteException{
     return  client.getLibrarianList();
  }

  @Override public String toStringArray() throws RemoteException
  {
    return client.toStringArray();
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
