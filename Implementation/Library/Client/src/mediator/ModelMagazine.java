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
     * Add a Magazine
     * @param magazine
     * Magazine object
     * @throws RemoteException
     */
    void addMagazine(Magazine magazine) throws RemoteException, SQLException;

    /**
     * Remove a Magazine by the id
     * @param id
     * The unique identification number
     * @throws RemoteException
     */
    void removeMagazine(int id) throws RemoteException, SQLException;

    /**
     * Return a list with all the magazines added
     * @return
     * Magazine List
     * @throws RemoteException
     * @throws SQLException
     */
    ArrayList<Magazine> getMagazineList() throws RemoteException, SQLException;
}
