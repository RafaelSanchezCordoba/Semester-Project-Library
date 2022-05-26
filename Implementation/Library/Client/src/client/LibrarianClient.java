package client;

import model.Librarian;

import java.io.Closeable;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * The client interface for Librarian.
 * @author Rafael Sánchez Córdoba.
 * @version 1.2 09/05/22.
 */
public interface LibrarianClient extends Closeable {
    /**
     * Add a librarian using the remote interface
     * @param librarian
     * The librarian object
     * @throws RemoteException
     */
    void addLibrarian(Librarian librarian) throws RemoteException;

    /**
     * The method remove a librarian by the SSN
     * @param SSN
     * The Social Security Number
     * @throws RemoteException
     */
    void removeLibrarian(String SSN) throws RemoteException;

    /**
     * Returns a list with all the librarians
     * @return
     * Librarian list
     * @throws RemoteException
     */
    ArrayList<Librarian> getLibrarianList() throws RemoteException;
}
