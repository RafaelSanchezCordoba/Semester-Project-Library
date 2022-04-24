package server;

import model.*;
import persistance.BookDAO;
import persistance.BookDAOImplementation;
import persistance.MagazineStorage;
import persistance.MagazineStorageImplementation;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;

public class Communicator extends UnicastRemoteObject implements RemoteBook, RemoteMagazine {
    private MagazineStorage magazineStorage;
    private BookDAO connector;
    private ArrayList<Object[]> list;

    public Communicator() throws RemoteException {
        magazineStorage = MagazineStorageImplementation.getInstance();
        connector = new BookDAOImplementation("org.postgresql.Driver","jdbc:postgresql://tai.db.elephantsql.com/naeoxool",
            "naeoxool","1eiSjWkSFVXj15hc0j47p_js1irgaDWr");
        connector.start();

        list=connector.getBookList();

    }

    public ArrayList<Object []> getBookList() throws RemoteException{
        list  = connector.getBookList();
        return list;
    }

    @Override
    public void addBook(Book book) throws RemoteException {
        //We need DB
        // resolve nul values
        connector.addBook(book.getId(), book.getIsbn(), book.getPublisher(),
            book.getTitle(), book.getYear_published(), book.getAuthor(),
            book.getEdition(),1802007570);
        list=connector.getBookList();

    }

    @Override
    public void removeBook(int id) throws RemoteException {
        connector.removeBook(id);
    }


    @Override
    public void addMagazine(Magazine magazine) throws RemoteException, SQLException {
        magazineStorage.addMagazine(magazine);
    }

    @Override
    public void removeMagazine(int id) throws RemoteException, SQLException
    {
        magazineStorage.removeMagazine(id);
    }
    public ArrayList<Magazine> getMagazineList() throws SQLException{
        return magazineStorage.getMagazineList();
    }

}


