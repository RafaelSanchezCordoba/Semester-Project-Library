package persistance;

import model.Librarian;
import persistance.LibrarianDAO;
import server.storage.LibrarianStorage;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * The Adapter for librarian.
 * @author Rafael Sánchez Córdoba.
 * @version 1.2 09/05/22
 */
public class AdapterLibrarianDAO implements LibrarianStorage {
    private LibrarianDAO librarianDAO;

    /**
     * Public constructor that set the LibrarianDAO
     * @param librarianDAO
     * LibrarianDAO object
     */
    public AdapterLibrarianDAO(LibrarianDAO librarianDAO) {
        this.librarianDAO = librarianDAO;
    }

    /**
     * Add a Librarian
     * @param librarian
     * Librarian object
     * @throws RemoteException
     */
    @Override
    public void addLibrarian(Librarian librarian) throws SQLException {
        librarianDAO.addLibrarian(librarian);
    }

    /**
     * Remove a Librarian by the SSN
     * @param SSN
     * The Social Security Number
     * @throws RemoteException
     */
    @Override
    public void removeLibrarian(String SSN) throws SQLException {
        librarianDAO.removeLibrarian(SSN);
    }

    /**
     * Return a list with all the librarians added
     * @return
     * Librarian List
     * @throws RemoteException
     * @throws SQLException
     */
    @Override
    public ArrayList<Librarian> getLibrarianList() throws SQLException {
        return librarianDAO.getLibrarianList();
    }
}
