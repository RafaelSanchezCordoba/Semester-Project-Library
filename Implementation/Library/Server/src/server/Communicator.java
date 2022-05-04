package server;

import fakeStorage.FakeStorage;
import fakeStorage.FakeStorageImplementation;
import model.*;
import persistance.BookDAO;
import persistance.BookDAOImplementation;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;

public class Communicator extends UnicastRemoteObject implements RemoteBook, RemoteMagazine,RemoteLibrarian {
    private MagazineStorage magazineStorage;
    private BookDAO connector;
    private ArrayList<Object[]> list;
    private FakeStorage fakeStorage;

    public Communicator(MagazineStorage magazineStorage) throws RemoteException {
        this.magazineStorage=magazineStorage;
        connector = new BookDAOImplementation("org.postgresql.Driver","jdbc:postgresql://tai.db.elephantsql.com/naeoxool",
            "naeoxool","1eiSjWkSFVXj15hc0j47p_js1irgaDWr");
        connector.start();

        list=connector.getBookList();
        fakeStorage = new FakeStorageImplementation();


    }
    public ArrayList<Librarian> getLibrarianList(){
        return fakeStorage.getLibrarianList();
    }

    @Override public void removeLibrarianBySsn(int ssn) throws RemoteException
    {

    }


    public void addLibrarian(Librarian librarian){
        fakeStorage.addLibrarian(librarian);
    }
    public void removeLibrarian(Librarian librarian){
        fakeStorage.removeLibrarian(librarian);
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
        System.out.println("edfghjk");
        magazineStorage.addMagazine(magazine);
    }

    @Override
    public void removeMagazine(int id) throws RemoteException, SQLException
    {
        magazineStorage.removeMagazine(id);
    }
    @Override
    public ArrayList<Magazine> getMagazineList()
        throws SQLException, RemoteException
    {
        return magazineStorage.getMagazineList();
    }

}


