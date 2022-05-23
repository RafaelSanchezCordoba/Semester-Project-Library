package client;

import model.Book;
import model.LibraryUser;
import model.LoanBook;


import java.io.Closeable;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface LoanBookClient extends Closeable {
  /**
   * Add loan book method
   * @param loanBook
   * The loan book passed as an argument
   * @throws SQLException
   * @throws RemoteException
   */
  void addBookLoan(LoanBook loanBook) throws SQLException, RemoteException;

  /**
   * Get the available books
   * @return
   * All the available books
   * @throws SQLException
   * @throws RemoteException
   */
  ArrayList<Book> getAvailableBookList() throws SQLException, RemoteException;

  /**
   * Get a user with a specific social security number
   * @param ssn
   * The social security number passed as an argument
   * @return
   * @throws RemoteException
   */
  LibraryUser getUser(String ssn) throws RemoteException;

  /**
   * Get the loan of the user with the social security number passed as an argument
   * @param ssn
   * The social security number
   * @return
   * @throws RemoteException
   */
  ArrayList<LoanBook> getUserBookLoans(String ssn) throws RemoteException;

  /**
   * Return book method
   * @param loan_id
   * The unique identification number of the loan
   * @throws RemoteException
   */
  void returnBook(int loan_id) throws RemoteException;
}
