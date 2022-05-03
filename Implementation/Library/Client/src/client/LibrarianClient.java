package client;

import model.Librarian;

import java.io.Closeable;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * The client interface for librarian.
 * @author Franciszek Nurkiewicz
 * @version 1.0 02/04/2022
 */
public interface LibrarianClient extends Closeable {

    void addLibrarian(Librarian librarian) throws RemoteException;

    void removeLibrarian(String ssn) throws RemoteException;

    ArrayList<Object[]>getLibrarianList() throws RemoteException;

}
