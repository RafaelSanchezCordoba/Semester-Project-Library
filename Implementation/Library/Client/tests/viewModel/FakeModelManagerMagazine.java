package viewModel;

import mediator.ModelMagazine;
import model.Magazine;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public class FakeModelManagerMagazine implements ModelMagazine
{
  private final ArrayList<Magazine> list;
  private final PropertyChangeSupport support;

  public FakeModelManagerMagazine()
  {
    list=new ArrayList<>();
    support=new PropertyChangeSupport(this);
  }

  @Override public void addMagazine(Magazine magazine)
      throws RemoteException
  {
    list.add(magazine);
    support.firePropertyChange("newMagazine",null,magazine);
  }

  @Override public void removeMagazine(int id)
      throws RemoteException
  {
   for (int i=0;i<list.size();i++)
   {
     if (list.get(i).getId()==id)
     {
       list.remove(list.get(i));
     }
   }
   support.firePropertyChange("removeMagazine",null,id);
  }

  @Override public ArrayList<Magazine> getMagazineList()
      throws RemoteException
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
