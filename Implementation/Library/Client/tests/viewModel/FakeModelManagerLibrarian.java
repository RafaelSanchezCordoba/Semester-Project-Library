package viewModel;

import mediator.ModelLibrarian;
import model.Librarian;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public class FakeModelManagerLibrarian implements ModelLibrarian
{
  private ArrayList<Librarian> list;
  private final PropertyChangeSupport support;

  public FakeModelManagerLibrarian(ArrayList<Librarian> list)
  {
    this.list = list;
    support = new PropertyChangeSupport(this);
  }

  @Override public void addLibrarian(Librarian librarian) throws RemoteException
  {
    list.add(librarian);
    support.firePropertyChange("newLibrarian", null, librarian);
  }

  @Override public void removeLibrarian(String SSN)
      throws RemoteException, SQLException
  {
    for (int i=0;i<list.size();i++)
    {
      if (list.get(i).getSsn().equals(SSN))
      {
        list.remove(list.get(i));
      }
    }
    support.firePropertyChange("removeLibrarian",null,SSN);
  }


  @Override public ArrayList<Librarian> getLibrarianList()
      throws RemoteException
  {
    return list;  }

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