package mediator;

import model.Magazine;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * The model interface for magazine.
 * @author Rafael Sánchez Córdoba.
 * @version 1.0 09/04/22
 */
public interface ModelMagazine extends PropertyChangeSubject{
    /**
     * Add magazine method
     * @param magazine
     * The magazine passed as an argument
     * @throws RemoteException
     * @throws SQLException
     */
    void addMagazine(Magazine magazine) throws RemoteException;

    /**
     * Remove a magazine with a specific identification number
     * @param id
     * The unique identification number passed as an argument
     * @throws RemoteException
     * @throws SQLException
     */
    void removeMagazine(int id) throws RemoteException;

    /**
     * Get magazine list method
     * @return
     * The magazines in the list
     * @throws SQLException
     * @throws RemoteException
     */
    ArrayList<Magazine> getMagazineList() throws RemoteException;
}
