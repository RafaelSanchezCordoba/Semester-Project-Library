package server.storage;

import model.Book;
import model.LibraryUser;
import model.LoanBook;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Public LoanBookStorage interface that is
 * implemented by AdapterLoanBookDAO class
 * The purpose of this class is to provide abstract
 * interface with all methods for class AdapterLoanBookDAO
 */
public interface LoanBookStorage
{
  /**
   * Method adding a book to loan
   * @param loanBook
   * LoanBook
   * @throws RemoteException
   * SQL exception with the message "No keys generated" can be caught
   * from LoanBookDAOImplementation class
   */
  void addLoanBook(LoanBook loanBook) throws  RemoteException;

  /**
   * Method returning ArrayList of all books available to loan
   * @return
   * ArrayList of books available to loan
   * @throws RemoteException
   */
  ArrayList<Book> getAvailableBooks() throws  RemoteException;

  /**
   * Method returning a user after it's ssn
   * @param ssn
   * Social security number
   * @return
   * User with given ssn number
   * @throws RemoteException
   */
  LibraryUser getUser(String ssn) throws RemoteException;

  /**
   * Method returning loans of user after it's ssn
   * @param ssn
   * Social security number
   * @return
   * Loans of user after it's ssn
   * @throws RemoteException
   */
  ArrayList<LoanBook> getUserBookLoans(String ssn) throws RemoteException;

  /**
   * Method returning a book from being loaned to the library
   * @param loan_id
   * ID number of loan
   * @throws RemoteException
   */
  void returnBook(int loan_id) throws RemoteException;
}
