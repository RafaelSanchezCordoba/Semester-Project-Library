package viewModel;

import mediator.ModelLibraryUser;
import model.LibraryUser;
import model.Magazine;


import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public class FakeModelManagerLibraryUser implements ModelLibraryUser
{

    private final ArrayList<LibraryUser> list;
    private final PropertyChangeSupport support;

    public FakeModelManagerLibraryUser() {
        list=new ArrayList<>();
        support=new PropertyChangeSupport(this);
    }

    @Override
    public void addLibraryUser(LibraryUser libraryUser) throws RemoteException, SQLException {
        list.add(libraryUser);
        support.firePropertyChange("addLibraryUser", null, libraryUser);
    }

    @Override
    public void removeLibraryUser(String ssn) throws RemoteException, SQLException {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getSSN().equals(ssn)) {
                list.remove(list.get(i));
            }
        }
        support.firePropertyChange("removeLibraryUser", null, ssn);
    }

    @Override
    public ArrayList<LibraryUser> getLibraryUserList() throws RemoteException, SQLException {
        return list;
    }

    @Override public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    @Override public void addPropertyChangeListener(String name, PropertyChangeListener listener) {
        support.addPropertyChangeListener(name,listener);
    }

    @Override public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }

    @Override public void removePropertyChangeListener(String name, PropertyChangeListener listener){
        support.removePropertyChangeListener(name,listener);
    }

}
