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
    void addLibrarian(Librarian librarian) throws RemoteException;
    void removeLibrarian(int SSN) throws RemoteException;
    ArrayList<Librarian> getLibrarianList() throws RemoteException;
}
