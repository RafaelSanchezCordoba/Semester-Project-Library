package client;

import model.Librarian;
import server.RemoteLibrarian;
import server.RemoteMagazine;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * The client implementation for Librarian that extends <code>UnicastRemoteObject</code> and implements <code>LibrarianCLient</code>.
 * @author Rafael Sánchez Córdoba.
 * @version 1.2 09/05/22.
 */
public class LibrarianClientImplementation extends UnicastRemoteObject implements LibrarianClient{
    private final RemoteLibrarian remoteLibrarian;

    /**
     * The constructor that create and set the Registry.
     * @param host
     * The name of the host
     * @param port
     * The port number
     * @throws IOException
     *
     * @throws NotBoundException
     */
    public LibrarianClientImplementation(String  host, int port) throws IOException, NotBoundException
    {

        Registry registry = LocateRegistry.getRegistry(host, port);
        remoteLibrarian = (RemoteLibrarian) registry.lookup("librarian");

    }

    /**
     * Add a librarian using the remote interface
     * @param librarian
     * The librarian object
     * @throws RemoteException
     */
    @Override
    public void addLibrarian(Librarian librarian) throws RemoteException, SQLException {
        remoteLibrarian.addLibrarian(librarian);
    }

    /**
     * The method remove a librarian by the SSN
     * @param SSN
     * The Social Security Number
     * @throws RemoteException
     */
    @Override
    public void removeLibrarian(int SSN) throws RemoteException, SQLException {
        remoteLibrarian.removeLibrarian(SSN);
    }

    /**
     * Returns a list with all the librarians
     * @return
     * Librarian list
     * @throws RemoteException
     */
    @Override
    public ArrayList<Librarian> getLibrarianList() throws RemoteException, SQLException {
        return remoteLibrarian.getLibrarianList();
    }

    /**
     * Close the remote object
     * @throws IOException
     */
    @Override
    public void close() throws IOException {
        UnicastRemoteObject.unexportObject(this,true);
    }
}
