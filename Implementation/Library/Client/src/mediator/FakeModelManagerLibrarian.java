package mediator;

import model.Librarian;
import model.LibrarianList;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public class FakeModelManagerLibrarian implements ModelLibrarian
{
  private LibrarianList list;
  private final PropertyChangeSupport support;

  public FakeModelManagerLibrarian(LibrarianList list)
  {
    this.list = list;
    support = new PropertyChangeSupport(this);
  }

  @Override public void addLibrarian(Librarian librarian) throws RemoteException
  {
    list.addLibrarian(librarian);
    support.firePropertyChange("newLibrarian", null, librarian);
  }

<<<<<<< Updated upstream
  @Override public void removeLibrarian(int SSN)
=======
<<<<<<< HEAD
  @Override public void removeLibrarian(String SSN)
=======
  @Override public void removeLibrarian(int SSN)
>>>>>>> 7e718619a38eaa66df02ddfe9864aa4893dd4e7c
>>>>>>> Stashed changes
      throws RemoteException, SQLException
  {
    list.removeLibrarianBySsn(SSN);
    support.firePropertyChange("removeLibrarian",null,SSN);
  }


  @Override public ArrayList<Librarian> getLibrarianList()
      throws RemoteException
  {
    return list.getLibrarianList();
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