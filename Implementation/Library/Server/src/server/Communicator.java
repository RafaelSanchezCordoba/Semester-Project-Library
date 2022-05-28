package server;

import model.*;
import server.storage.*;

import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Public communicator class communicating between module Server package server
 * with Shared side of the application.
 * Implementing all interfaces from Shared module, server package: RemoteBook,
 * RemoteMagazine, RemoteLibrarian, RemoteLibraryUser,RemoteLoanBook,RemoteLoanMagazine
 * The class is also extending UnicastRemoteObject.
 */
public class Communicator extends UnicastRemoteObject implements RemoteBook, RemoteMagazine, RemoteLibrarian, RemoteLibraryUser,RemoteLoanBook,RemoteLoanMagazine{
    private MagazineStorage magazineStorage;
    private BookStorage bookStorage;
    private LibrarianStorage librarianStorage;
    private LibraryUserStorage libraryUserStorage;
    private LoanMagazineStorage loanMagazineStorage;
    private LoanBookStorage loanBookStorage;
    private PropertyChangeSupport support;
//    private GenreStorage genreStorage;


    /**
     * Public Communicator six parameter constructor
     * @param magazineStorage
     * interface MagazineStorage from server module and package
     * @param bookStorage
     * interface LibrarianStorage from server module and package
     * @param librarianStorage
     * interface LibraryUserStorage from server module and package
     * @param libraryUserStorage
     * interface loanMagazineStorage from server module and package
     * @param loanMagazineStorage
     * interface loanBookStorage from server module and package
     * @param loanBookStorage
     * @throws RemoteException
     */
    public Communicator(MagazineStorage magazineStorage, BookStorage bookStorage, LibrarianStorage librarianStorage,LibraryUserStorage libraryUserStorage, LoanMagazineStorage loanMagazineStorage, LoanBookStorage loanBookStorage) throws RemoteException {
        this.magazineStorage = magazineStorage;
        this.bookStorage = bookStorage;
        this.librarianStorage = librarianStorage;
        this.libraryUserStorage = libraryUserStorage;
        this.loanMagazineStorage = loanMagazineStorage;
        this.loanBookStorage= loanBookStorage;

//        this.genreStorage = genreStorage;
    }

    /**
     * Method adding a book
     * @param book
     * Book
     * @throws SQLException
     * SQL exception with the message "no keys generated" can be caught
     * from BookDAOImplementation class
     */
    @Override
    public void addBook(Book book) throws RemoteException {
        bookStorage.addBook(book);

    }

    /**
     * Method removing book after it's id
     * @param id
     * Id of the book
     * @throws SQLException
     */
    @Override
    public void removeBook(int id) throws RemoteException
    {
        bookStorage.removeBook(id);
    }

    /**
     * Method returning list of the books as ArrayList
     * @return
     * List of the books as ArrayList
     * @throws SQLException
     */
    @Override public ArrayList<Book> getBookList() throws RemoteException
    {
        return bookStorage.getBookList();
    }

    /**
     * Method returning list of genres as
     * ArrayList from GenreList class
     * @return
     * List of genres as ArrayList
     * @throws SQLException
     */
    @Override public GenreList getGenreList()
        throws RemoteException
    {
        return bookStorage.getGenreList();
    }

    /**
     * Method adding a magazine
     * @param magazine
     * Magazine
     * @throws SQLException
     * SQL exception with the message "No keys generated" can
     * be caught from MagazineDAOImplementation
     */
    @Override
    public void addMagazine(Magazine magazine) throws RemoteException {
        magazineStorage.addMagazine(magazine);
    }

    /**
     * Method removing magazine after it's id
     * @param id
     * Id of the magazine
     * @throws SQLException
     * SQL exception with the message "magazine is being lent"
     * can be caught from MagazineDAOImplementation
     */
    @Override
    public void removeMagazine(int id) throws RemoteException
    {
        magazineStorage.removeMagazine(id);
    }

    /**
     * Method returning list of the magazine as ArrayList
     * @return
     * List of the magazine as ArrayList
     * @throws SQLException
     */
    @Override public ArrayList<Magazine> getMagazineList()
        throws  RemoteException
    {
        return magazineStorage.getMagazineList();
    }

    /**
     * Add a Librarian
     * @param librarian
     * Librarian object
     * @throws RemoteException
     */
    @Override public void addLibrarian(Librarian librarian) throws RemoteException {
        librarianStorage.addLibrarian(librarian);
    }

    /**
     * Remove a Librarian by the SSN
     * @param SSN
     * The Social Security Number
     * @throws RemoteException
     */
    @Override public void removeLibrarian(String SSN) throws RemoteException {
        librarianStorage.removeLibrarian(SSN);
    }

    /**
     * Return a list with all the librarians added
     * @return
     * Librarian List
     * @throws RemoteException
     * @throws SQLException
     */
    @Override public ArrayList<Librarian> getLibrarianList() throws RemoteException {
        return librarianStorage.getLibrarianList();
    }

    /**
     * Method adding a library user
     * @param libraryUser
     * Library user object
     * @throws SQLException
     */
    @Override public void addLibraryUser(LibraryUser libraryUser)
        throws RemoteException
    {
        libraryUserStorage.addLibraryUser(libraryUser);
    }

    /**
     * Remove a library user by the ssn
     * @param ssn
     * Social security number
     * @throws SQLException
     */
    @Override public void removeLibraryUser(String ssn)
        throws RemoteException
    {
        libraryUserStorage.removeLibraryUser(ssn);
    }

    /**
     * Return a list with all the library users added
     * @return
     * Library users list
     * @throws SQLException
     */
    @Override public ArrayList<LibraryUser> getLibraryUserList()
        throws RemoteException
    {
        return libraryUserStorage.getLibraryUserList();
    }

    /**
     * Method adding a magazine to loan
     * @param loanMagazine
     * MagazineBook
     * @throws SQLException
     * SQL exception with the message "No keys generated" can be caught
     * from LoanMagazineDAOImplementation class
     */
    @Override public void addMagazineLoan(LoanMagazine loanMagazine)
        throws  RemoteException
    {
        loanMagazineStorage.addMagazineLoan(loanMagazine);
    }

    /**
     * Method returning ArrayList of all magazines to loan
     * @return
     * ArrayList of magazines available to loan
     * @throws SQLException
     */
    @Override public ArrayList<Magazine> getAvailableMagazineList()
        throws  RemoteException
    {
        return loanMagazineStorage.getAvailableMagazineList();
    }

    /**
     * Method adding a book to loan
     * @param loanBook
     * LoanBook
     * @throws SQLException
     * SQL exception with message "No keys generated" can be caught
     * from LoanBookDAOImplementation class
     */
    @Override public void addBookLoan(LoanBook loanBook)
        throws  RemoteException
    {
        loanBookStorage.addLoanBook(loanBook);
    }

    /**
     * Method returning ArrayList of all books available to loan
     * @return
     * ArrayList of books available to loan
     * @throws SQLException
     */
    @Override public ArrayList<Book> getAvailableBookList()
        throws  RemoteException
    {
        return loanBookStorage.getAvailableBooks();
    }

    /**
     * Method returning a user after it's snn
     * @param ssn
     * Social security number
     * @return
     * User with given snn number
     * @throws SQLException
     */
    @Override public LibraryUser getUser(String ssn) throws RemoteException
    {
        return loanMagazineStorage.getUser(ssn);
    }

    /**
     * Method returning loans of user after it's ssn
     * @param ssn
     * Social security number
     * @return
     * Loans of user after it's ssn
     * @throws SQLException
     */
    @Override public ArrayList<LoanBook> getUserBookLoans(String ssn)
        throws RemoteException
    {
        return loanBookStorage.getUserBookLoans(ssn);
    }

    /**
     * Method returning a book from being loaned to the library
     * @param loan_id
     * ID number of loan
     * @throws SQLException
     */
    @Override public void returnBook(int loan_id) throws RemoteException
    {
        loanBookStorage.returnBook(loan_id);
    }

    //    @Override public ArrayList<Magazine> getLoanedMagazines(String ssn)
    //        throws RemoteException
    //    {
    //        return loanMagazineStorage.getLoanedMagazines(ssn);
    //    }

    /**
     * Method returning a magazine from being loaned to the library
     * @param loan_id
     * ID number of loan
     * @throws SQLException
     */
    @Override public void returnMagazine(int loan_id) throws RemoteException {
        loanMagazineStorage.returnMagazine(loan_id);
    }

    /**
     * Method returning loans of user after it's ssn
     * @param ssn
     * Social security number
     * @return
     * Loans of user after it's ssn
     * @throws SQLException
     */
    @Override public ArrayList<LoanMagazine> getUserLoans(String ssn) throws RemoteException
    {
        return loanMagazineStorage.getUsersLoans(ssn);
    }
}


