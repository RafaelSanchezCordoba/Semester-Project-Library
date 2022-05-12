package client;

import model.Librarian;

import java.io.Closeable;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * The client interface for Librarian.
 * @author Rafael Sánchez Córdoba.
 * @version 1.2 09/05/22.
 */
public interface LibrarianClient extends Closeable {
    void addLibrarian(Librarian librarian) throws RemoteException, SQLException;
    void removeLibrarian(String SSN) throws RemoteException, SQLException;
    ArrayList<Librarian> getLibrarianList() throws RemoteException, SQLException;
}
