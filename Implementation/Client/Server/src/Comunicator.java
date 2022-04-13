

import dk.via.remote.observer.RemotePropertyChangeListener;
import dk.via.remote.observer.RemotePropertyChangeSupport;
import model.*;
import server.*;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Comunicator extends UnicastRemoteObject implements RemoteBook, RemoteMagazine {

    private DBConnector connector = null;
    private ArrayList<Object[]> list;

    public Comunicator() throws RemoteException {
        connector = new DBConnector("org.postgresql.Driver","jdbc:postgresql://localhost:5433/postgres",
            "postgres","dragon93");
        connector.start();
        list=connector.getBookList();

    }

    public ArrayList<Object []> getBookList() throws RemoteException{
        return list;
    }

    @Override
    public void addBook(Book book) throws RemoteException {
        //We need DB
        connector.addBook(String.valueOf(book.getId()), book.getPublisher(), book.getTitle(),
                            book.getIsbn(), (String.valueOf(book.getYear_published())),
                        null,null,String.valueOf(book.getEdition()),null);
        list=connector.getBookList();

    }

    @Override
    public void removeBook(int id) throws RemoteException {
        connector.removeBook(String.valueOf(id));
    }


    @Override
    public void addMagazine(Magazine magazine) throws RemoteException {
        //
    }

    @Override
    public void removeMagazine(int id) throws RemoteException{
        //
    }

}


