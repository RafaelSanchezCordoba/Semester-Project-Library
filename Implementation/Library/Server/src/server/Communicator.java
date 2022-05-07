package server;

import fakeStorage.FakeStorage;
import model.*;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;

public class Communicator extends UnicastRemoteObject implements RemoteBook, RemoteMagazine,RemoteLibrarian{
    private MagazineStorage magazineStorage;
    private BookStorage bookStorage;
    private FakeStorage librarianStorage;
//    private GenreStorage genreStorage;


    public Communicator(MagazineStorage magazineStorage, BookStorage bookStorage, FakeStorage librarianStorage) throws RemoteException {
        this.magazineStorage = magazineStorage;
        this.bookStorage = bookStorage;
        this.librarianStorage = librarianStorage;
//        this.genreStorage = genreStorage;
    }

//    @Override public void addGenre(Genre genre)
//        throws SQLException, RemoteException
//    {
//        genreStorage.addGenre(genre);
//    }
//
//    @Override public void removeGenre(int id)
//        throws SQLException, RemoteException
//    {
//        genreStorage.removeGenre(id);
//    }
//
//    @Override public ArrayList<Genre> getGenreList()
//        throws SQLException, RemoteException
//    {
//        return genreStorage.getGenreList();
//    }

    @Override
    public void addBook(Book book) throws RemoteException, SQLException {
        bookStorage.addBook(book);

    }

    @Override
    public void removeBook(int id) throws RemoteException, SQLException
    {
        bookStorage.removeBook(id);
    }

    @Override public ArrayList<Book> getBookList() throws RemoteException, SQLException
    {
        return bookStorage.getBookList();
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
    @Override
    public ArrayList<Magazine> getMagazineList()
        throws SQLException, RemoteException
    {
        return magazineStorage.getMagazineList();
    }

    @Override public void addLibrarian(Librarian librarian)
        throws RemoteException
    {

    }

    @Override public void removeLibrarian(Librarian librarian)
        throws RemoteException
    {

    }

    @Override public ArrayList<Librarian> getLibrarianList()
        throws RemoteException
    {
        return null;
    }

    @Override public void removeLibrarianBySsn(int ssn) throws RemoteException
    {

    }
}


