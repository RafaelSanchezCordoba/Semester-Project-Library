package mediator;

import model.Librarian;
import model.LibrarianList;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class FakeLibrarianModel implements ModelLibrarian
{
private LibrarianList list;
  private final PropertyChangeSupport support;

  public FakeLibrarianModel(LibrarianList list)
  {
    this.list = list;
    support = new PropertyChangeSupport(this);
  }

  @Override public void addLibrarian(Librarian librarian) throws RemoteException
  {
    list.addLibrarian(librarian);
    support.firePropertyChange("update", null, list.getLibrarianList());
  }

  @Override public void removeLibrarian(Librarian librarian)
      throws RemoteException
  {
    list.removeLibrarian(librarian);
    support.firePropertyChange("update",null,list.getLibrarianList());
  }

  @Override public ArrayList<Librarian> getLibrarianList()
      throws RemoteException
  {
    return list.getLibrarianList();
  }

  @Override public String toStringArray() throws RemoteException
  {
    return list.toStringArray();
  }

  @Override public void addPropertyChangeListener(
      PropertyChangeListener listener)
  {
support.addPropertyChangeListener(listener);
  }

  @Override public void addPropertyChangeListener(String name,
      PropertyChangeListener listener)
  {
support.addPropertyChangeListener(name,listener);
  }

  @Override public void removePropertyChangeListener(
      PropertyChangeListener listener)
  {
support.removePropertyChangeListener(listener);
  }

  @Override public void removePropertyChangeListener(String name,
      PropertyChangeListener listener)
  {
support.removePropertyChangeListener(name,listener);
  }
}
