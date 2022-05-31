package persistance.adapters;

import model.Librarian;
import persistance.DAO.LibrarianDAO;
import server.storage.LibrarianStorage;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * The Adapter for librarian.
 * @author Rafael Sanchez Cordoba.
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
    public void addLibrarian(Librarian librarian)throws RemoteException {
        try
        {
            librarianDAO.addLibrarian(librarian);
        }catch (SQLException e){
            throw  new RemoteException(e.getMessage(),e);
        }

    }

    /**
     * Remove a Librarian by the SSN
     * @param SSN
     * The Social Security Number
     * @throws RemoteException
     */
    @Override
    public void removeLibrarian(String SSN) throws RemoteException {
        try
        {
            librarianDAO.removeLibrarian(SSN);
        }catch (SQLException e){
            throw new RemoteException(e.getMessage(),e);
        }

    }

    /**
     * Return a list with all the librarians added
     * @return
     * Librarian List
     * @throws RemoteException
     * @throws SQLException
     */
    @Override
    public ArrayList<Librarian> getLibrarianList() throws RemoteException {
        try
        {
            return librarianDAO.getLibrarianList();
        }catch (SQLException e){
            throw new RemoteException(e.getMessage(),e);
        }

    }
}
