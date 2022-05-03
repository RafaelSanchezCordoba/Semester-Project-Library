package client;

import model.Librarian;
import server.RemoteLibrarian;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class LibrarianClientImplementation extends UnicastRemoteObject implements LibrarianClient {

    private final RemoteLibrarian remoteLibrarian;
    public LibrarianClientImplementation(String host, int port) throws IOException, NotBoundException{
        Registry registry = LocateRegistry.getRegistry();
        remoteLibrarian = (RemoteLibrarian) registry.lookup("librarian");
    }

    @Override public void addLibrarian(Librarian librarian) throws RemoteException{
        remoteLibrarian.addLibrarian(librarian);
    }

    @Override public void removeLibrarian(String ssn) throws RemoteException{
        remoteLibrarian.removeLibrarian(ssn);
    }

    public ArrayList<Object[]> getLibrarianList() throws RemoteException{
        return remoteLibrarian.getLibrarianList();
    }

    @Override public void close() throws IOException{
        UnicastRemoteObject.unexportObject(this,true);
    }
}
