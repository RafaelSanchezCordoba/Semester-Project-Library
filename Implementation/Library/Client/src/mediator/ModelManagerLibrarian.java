package mediator;

import client.LibrarianClient;
import model.Librarian;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class ModelManagerLibrarian implements ModelLibrarian{
    private final LibrarianClient client;
    private final PropertyChangeSupport support;

    public ModelManagerLibrarian(LibrarianClient client){
        this.client = client;
        this.support = new PropertyChangeSupport(this);
    }

    @Override public void addLibrarian(Librarian librarian) throws RemoteException{
        client.addLibrarian(librarian);
    }

    @Override public void removeLibrarian(String ssn) throws RemoteException{
        client.removeLibrarian(ssn);
    }

    public ArrayList<Object[]> getLibrarianList(){
        try{
            return client.getLibrarianList();
        }catch (RemoteException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override public void addPropertyChangeListener(PropertyChangeListener listener){
        support.addPropertyChangeListener(listener);
    }

    @Override public void addPropertyChangeListener(String name, PropertyChangeListener listener){
        support.addPropertyChangeListener(name, listener);
    }

    @Override public void removePropertyChangeListener(PropertyChangeListener listener){
        support.removePropertyChangeListener(listener);
    }

    @Override
    public void removePropertyChangeListener(String name, PropertyChangeListener listener){
        support.removePropertyChangeListener(name, listener);
    }
}
