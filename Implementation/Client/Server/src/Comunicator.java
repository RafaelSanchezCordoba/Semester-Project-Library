import dk.via.remote.observer.RemotePropertyChangeListener;
import dk.via.remote.observer.RemotePropertyChangeSupport;
import model.Book;
import model.Magazine;
import model.MultimediaItem;
import server.RemoteBook;
import server.RemoteMagazine;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Comunicator extends UnicastRemoteObject implements RemoteBook, RemoteMagazine {


    public Comunicator() throws RemoteException {

    }

    @Override
    public void addBook(Book book) throws RemoteException {
        //We need DB
    }

    @Override
    public void removeBook(int id) throws RemoteException {
        //We need DB
    }


    @Override
    public void addMagazine(Magazine magazine) {
        //
    }

    @Override
    public void removeMagazine(int id) {
        //
    }

}


