package mediator;

import client.MagazineClient;
import model.Magazine;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;


public class ModelManagerMagazine implements ModelMagazine{
    private final MagazineClient client;
    private final PropertyChangeSupport support;


    public ModelManagerMagazine(MagazineClient client) {
        this.client = client;
        this.support = new PropertyChangeSupport(this);
    }

    @Override
    public void addMagazine(Magazine magazine) throws RemoteException {
        client.addMagazine(magazine);
    }

    @Override
    public void removeMagazine(int id) throws RemoteException {
        client.removeMagazine(id);
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
