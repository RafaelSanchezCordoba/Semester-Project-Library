package mediator;

import client.MagazineClient;
import model.Magazine;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * The model manager for magazine.
 * @author Rafael Sánchez Córdoba.
 * @version 1.0 09/04/22
 */

public class ModelManagerMagazine implements ModelMagazine{
    private final MagazineClient client;
    private final PropertyChangeSupport support;


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
    public void addMagazine(Magazine magazine)
    {

        try
        {
            client.addMagazine(magazine);
            System.out.println("client");
            support.firePropertyChange("newMagazine",null, magazine);
        }
        catch (RemoteException e)
        {
            e.printStackTrace();
        }

    }

    /**
     *
     * @param id
     * @throws RemoteException
     */
    @Override
    public void removeMagazine(int id) throws RemoteException
    {
        System.out.println(id);
        client.removeMagazine(id);
        support.firePropertyChange("removeMagazine", null, id);
    }

    /**
     * Add property change listener, just with the listener
     * @param listener
     * The listener
     */
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     * Add property change listener with a name and the listener
     * @param name
     * The name
     * @param listener
     * The listener
     */
    @Override
    public void addPropertyChangeListener(String name, PropertyChangeListener listener) {
        support.addPropertyChangeListener(name, listener);
    }

    /**
     * Remove property change listener, just with the listener
     * @param listener
     * The listener
     */
    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }

    /**
     * Remove property change listener with a name and the listener
     * @param name
     * The name
     * @param listener
     * The listener
     */
    @Override
    public void removePropertyChangeListener(String name, PropertyChangeListener listener) {
        support.removePropertyChangeListener(name, listener);
    }

    @Override public ArrayList<Magazine> getMagazineList() throws RemoteException
    {
        return client.getMagazineList();
    }
}
