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
     * Add a Magazine
     * @param magazine
     * Magazine object
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
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Remove a Magazine by the id
     * @param id
     * The unique identification number
     * @throws RemoteException
     */
    @Override
    public void removeMagazine(int id) throws RemoteException, SQLException
    {
        System.out.println(id);
        client.removeMagazine(id);
        support.firePropertyChange("removeMagazine", null, id);
    }

    /**
     * Return a list with all the magazines added
     * @return
     * Magazine List
     * @throws RemoteException
     * @throws SQLException
     */
    @Override public ArrayList<Magazine> getMagazineList() throws RemoteException, SQLException
    {
        return client.getMagazineList();
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
