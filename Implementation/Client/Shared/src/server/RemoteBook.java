package server;

import dk.via.remote.observer.RemotePropertyChangeListener;
import model.*;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Objects;

public interface RemoteBook extends Remote {
    void addBook(Book book) throws RemoteException;
    void removeBook(int id) throws RemoteException;
    ArrayList<Object[]> getBookList() throws RemoteException;

}
