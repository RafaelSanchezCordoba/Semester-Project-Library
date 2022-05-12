package server;

import model.*;
import server.storage.BookStorage;
import server.storage.LibrarianStorage;
import server.storage.MagazineStorage;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;

public class Communicator extends UnicastRemoteObject implements RemoteBook, RemoteMagazine,RemoteLibrarian{
    private MagazineStorage magazineStorage;
    private BookStorage bookStorage;
    private LibrarianStorage librarianStorage;
//    private GenreStorage genreStorage;


    public Communicator(MagazineStorage magazineStorage, BookStorage bookStorage, LibrarianStorage librarianStorage) throws RemoteException {
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

    @Override public void addLibrarian(Librarian librarian) throws RemoteException, SQLException {
        librarianStorage.addLibrarian(librarian);
    }

<<<<<<< Updated upstream
    @Override public void removeLibrarian(int SSN) throws RemoteException, SQLException {
=======
<<<<<<< HEAD
    @Override public void removeLibrarian(String SSN) throws RemoteException, SQLException {
=======
    @Override public void removeLibrarian(int SSN) throws RemoteException, SQLException {
>>>>>>> 7e718619a38eaa66df02ddfe9864aa4893dd4e7c
>>>>>>> Stashed changes
        librarianStorage.removeLibrarian(SSN);
    }

    @Override public ArrayList<Librarian> getLibrarianList() throws RemoteException, SQLException {
        return librarianStorage.getLibrarianList();
    }
}


