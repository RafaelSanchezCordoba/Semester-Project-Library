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
     *
     * @param magazine
     * @throws RemoteException
     */
    void addMagazine(Magazine magazine) throws RemoteException, SQLException;

    /**
     *
     * @param id
     * @throws RemoteException
     */
    void removeMagazine(int id) throws RemoteException, SQLException;

    ArrayList<Magazine> getMagazineList() throws RemoteException, SQLException;
}
