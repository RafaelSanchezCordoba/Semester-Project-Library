package server;

import model.*;
import server.storage.*;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;

public class Communicator extends UnicastRemoteObject implements RemoteBook, RemoteMagazine, RemoteLibrarian, RemoteLoanMagazine, RemoteLoanBook{
    private MagazineStorage magazineStorage;
    private BookStorage bookStorage;
    private LibrarianStorage librarianStorage;
    private LoanMagazineStorage loanMagazineStorage;
    private LoanBookStorage loanBookStorage;
//    private GenreStorage genreStorage;


    public Communicator(MagazineStorage magazineStorage, BookStorage bookStorage, LibrarianStorage librarianStorage, LoanMagazineStorage loanMagazineStorage, LoanBookStorage loanBookStorage) throws RemoteException {
        this.magazineStorage = magazineStorage;
        this.bookStorage = bookStorage;
        this.librarianStorage = librarianStorage;
        this.loanMagazineStorage = loanMagazineStorage;
        this.loanBookStorage= loanBookStorage;
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

    @Override public GenreList getGenreList()
        throws RemoteException, SQLException
    {
        return bookStorage.getGenreList();
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

    @Override public void removeLibrarian(String SSN) throws RemoteException, SQLException {
        librarianStorage.removeLibrarian(SSN);
    }

    @Override public ArrayList<Librarian> getLibrarianList() throws RemoteException, SQLException {
        return librarianStorage.getLibrarianList();
    }

    @Override
    public void addMagazineLoan(LoanMagazine loanMagazine) throws SQLException {
        loanMagazineStorage.addMagazineLoan(loanMagazine);
    }

    @Override
    public ArrayList<Magazine> getAvailableMagazineList() throws SQLException {
        return loanMagazineStorage.getAvailableMagazineList();
    }

    @Override public void addBookLoan(LoanBook loanBook)
        throws SQLException, RemoteException
    {
        loanBookStorage.addLoanBook(loanBook);
    }

    @Override public ArrayList<Book> getAvailableBookList()
        throws SQLException, RemoteException
    {
        return loanBookStorage.getAvailableBooks();
    }
}


