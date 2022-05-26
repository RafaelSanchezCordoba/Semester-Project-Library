package server;

import dk.via.remote.observer.RemotePropertyChangeListener;
import dk.via.remote.observer.RemotePropertyChangeSupport;
import model.*;
import server.storage.*;

import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;

public class Communicator extends UnicastRemoteObject implements RemoteBook, RemoteMagazine, RemoteLibrarian, RemoteLibraryUser,RemoteLoanBook,RemoteLoanMagazine{
    private MagazineStorage magazineStorage;
    private BookStorage bookStorage;
    private LibrarianStorage librarianStorage;
    private LibraryUserStorage libraryUserStorage;
    private LoanMagazineStorage loanMagazineStorage;
    private LoanBookStorage loanBookStorage;
    private final RemotePropertyChangeSupport<ArrayList<Book>> supportAvailableBook;
//    private GenreStorage genreStorage;


    public Communicator(MagazineStorage magazineStorage, BookStorage bookStorage, LibrarianStorage librarianStorage,LibraryUserStorage libraryUserStorage, LoanMagazineStorage loanMagazineStorage, LoanBookStorage loanBookStorage) throws RemoteException {
        this.magazineStorage = magazineStorage;
        this.bookStorage = bookStorage;
        this.librarianStorage = librarianStorage;
        this.libraryUserStorage = libraryUserStorage;
        this.loanMagazineStorage = loanMagazineStorage;
        this.loanBookStorage= loanBookStorage;

        this.supportAvailableBook = new RemotePropertyChangeSupport<>(this);
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
    public void addBook(Book book) throws RemoteException {
        bookStorage.addBook(book);

    }

    @Override
    public void removeBook(int id) throws RemoteException
    {
        bookStorage.removeBook(id);
    }

    @Override public ArrayList<Book> getBookList() throws RemoteException
    {
        return bookStorage.getBookList();
    }

    @Override public GenreList getGenreList()
        throws RemoteException
    {
        return bookStorage.getGenreList();
    }

    @Override
    public void addMagazine(Magazine magazine) throws RemoteException {
        magazineStorage.addMagazine(magazine);
    }

    @Override
    public void removeMagazine(int id) throws RemoteException
    {
        magazineStorage.removeMagazine(id);
    }
    @Override
    public ArrayList<Magazine> getMagazineList()
        throws  RemoteException
    {
        return magazineStorage.getMagazineList();
    }

    @Override public void addLibrarian(Librarian librarian) throws RemoteException {
        librarianStorage.addLibrarian(librarian);
    }

    @Override public void removeLibrarian(String SSN) throws RemoteException {
        librarianStorage.removeLibrarian(SSN);
    }

    @Override public ArrayList<Librarian> getLibrarianList() throws RemoteException {
        return librarianStorage.getLibrarianList();
    }

    @Override public void addLibraryUser(LibraryUser libraryUser)
        throws RemoteException
    {
        libraryUserStorage.addLibraryUser(libraryUser);
    }

    @Override public void removeLibraryUser(String ssn)
        throws RemoteException
    {
        libraryUserStorage.removeLibraryUser(ssn);
    }

    @Override public ArrayList<LibraryUser> getLibraryUserList()
        throws RemoteException
    {
        return libraryUserStorage.getLibraryUserList();
    }
    @Override
    public void addMagazineLoan(LoanMagazine loanMagazine)
        throws  RemoteException
    {
        loanMagazineStorage.addMagazineLoan(loanMagazine);
    }

    @Override
    public ArrayList<Magazine> getAvailableMagazineList()
        throws  RemoteException
    {
        return loanMagazineStorage.getAvailableMagazineList();
    }

    @Override public void addBookLoan(LoanBook loanBook)
        throws  RemoteException
    {
        loanBookStorage.addLoanBook(loanBook);
    }

    @Override public ArrayList<Book> getAvailableBookList()
        throws  RemoteException
    {
        return loanBookStorage.getAvailableBooks();
    }
    public LibraryUser getUser(String ssn) throws RemoteException
    {
        return loanMagazineStorage.getUser(ssn);
    }

    @Override public ArrayList<LoanBook> getUserBookLoans(String ssn)
        throws RemoteException
    {
        return loanBookStorage.getUserBookLoans(ssn);
    }

    @Override public void returnBook(int loan_id) throws RemoteException
    {
        loanBookStorage.returnBook(loan_id);
    }

    @Override
    public void addPropertyChangeListener(RemotePropertyChangeListener<ArrayList<Book>> listener) throws RemoteException{
        supportAvailableBook.addPropertyChangeListener(listener);
    }

    @Override
    public void removePropertyChangeListener(RemotePropertyChangeListener<ArrayList<Book>> listener) throws RemoteException{
        supportAvailableBook.removePropertyChangeListener(listener);
    }

    //    @Override public ArrayList<Magazine> getLoanedMagazines(String ssn)
    //        throws RemoteException
    //    {
    //        return loanMagazineStorage.getLoanedMagazines(ssn);
    //    }

    @Override public void returnMagazine(int loan_id) throws RemoteException {
        loanMagazineStorage.returnMagazine(loan_id);
    }

    @Override public ArrayList<LoanMagazine> getUserLoans(String ssn) throws RemoteException
    {
        return loanMagazineStorage.getUsersLoans(ssn);
    }
}


