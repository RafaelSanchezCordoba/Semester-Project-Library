package server;

import dk.via.remote.observer.RemotePropertyChangeListener;
import model.Book;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteBook extends Remote {
    void addBook(Book book) throws RemoteException;
    void removeBook(int id) throws RemoteException;

}
