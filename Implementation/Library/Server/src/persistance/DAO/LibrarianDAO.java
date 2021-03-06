package persistance.DAO;

import model.Librarian;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * The LibrarianDAO interface.
 * @author Rafael Sanchez Cordoba.
 * @version 1.2 09/05/22
 */
public interface LibrarianDAO {
    /**
     * Add a Librarian
     * @param librarian
     * Librarian object
     * @throws RemoteException
     */
    void addLibrarian(Librarian librarian) throws SQLException;

    /**
     * Remove a Librarian by the SSN
     * @param SSN
     * The Social Security Number
     * @throws RemoteException
     */
    void removeLibrarian(String SSN) throws SQLException;

    /**
     * Return a list with all the librarians added
     * @return
     * Librarian List
     * @throws RemoteException
     * @throws SQLException
     */
    ArrayList<Librarian> getLibrarianList() throws SQLException;
}
