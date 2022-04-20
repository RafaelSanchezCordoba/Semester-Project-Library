package mediator;

import model.Magazine;

import java.rmi.RemoteException;

/**
 * The model interface for magazine.
 * @author Rafael Sánchez Córdoba.
 * @version 1.0 09/04/22
 */
public interface ModelMagazine extends PropertyChangeSubject{
    /**
     *
     * @param magazine
     * @throws RemoteException
     */
    void addMagazine(Magazine magazine) throws RemoteException;

    /**
     *
     * @param id
     * @throws RemoteException
     */
    void removeMagazine(int id) throws RemoteException;
}
