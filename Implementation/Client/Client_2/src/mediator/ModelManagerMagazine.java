package mediator;

import client.MagazineClient;
import model.Magazine;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;

<<<<<<< HEAD

=======
/**
 * The model manager for magazine.
 * @author Rafael Sánchez Córdoba.
 * @version 1.0 09/04/22
 */
>>>>>>> 140f6a0c353514d0b2e9badd881b8031c2386050
public class ModelManagerMagazine implements ModelMagazine{
    private final MagazineClient client;
    private final PropertyChangeSupport support;

<<<<<<< HEAD

=======
    /**
     *
     * @param client
     */
>>>>>>> 140f6a0c353514d0b2e9badd881b8031c2386050
    public ModelManagerMagazine(MagazineClient client) {
        this.client = client;
        this.support = new PropertyChangeSupport(this);
    }

    /**
     *
     * @param magazine
     * @throws RemoteException
     */
    @Override
    public void addMagazine(Magazine magazine) throws RemoteException {
        client.addMagazine(magazine);
    }

    /**
     *
     * @param id
     * @throws RemoteException
     */
    @Override
    public void removeMagazine(int id) throws RemoteException {
        client.removeMagazine(id);
    }

    /**
     *
     * @param listener
     */
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     *
     * @param name
     * @param listener
     */
    @Override
    public void addPropertyChangeListener(String name, PropertyChangeListener listener) {
        support.addPropertyChangeListener(name, listener);
    }

    /**
     *
     * @param listener
     */
    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }

    /**
     *
     * @param name
     * @param listener
     */
    @Override
    public void removePropertyChangeListener(String name, PropertyChangeListener listener) {
        support.removePropertyChangeListener(name, listener);
    }
}
