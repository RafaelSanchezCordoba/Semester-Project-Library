package mediator;

import model.Magazine;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public class FakeModelManagerMagazine implements ModelMagazine
{
  private ArrayList<Magazine> list;
  private PropertyChangeSupport support;

  public FakeModelManagerMagazine()
  {
    list=new ArrayList<>();
    support=new PropertyChangeSupport(this);
  }

  @Override public void addMagazine(Magazine magazine)
      throws RemoteException, SQLException
  {
    list.add(magazine);
  }

  @Override public void removeMagazine(int id)
      throws RemoteException, SQLException
  {
   for (int i=0;i<list.size();i++)
   {
     if (list.get(i).getId()==id)
     {
       list.remove(list.get(i));
     }
   }
  }

  @Override public ArrayList<Magazine> getMagazineList()
      throws RemoteException, SQLException
  {
    return list;
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
