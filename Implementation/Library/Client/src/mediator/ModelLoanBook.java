package mediator;

import model.Book;
import model.LibraryUser;
import model.LoanBook;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ModelLoanBook extends PropertyChangeSubject {
  /**
   * Add loan book method
   * @param loanBook
   * The loan book passed as an argument
   * @throws SQLException
   * @throws RemoteException
   */
  void addLoanBook(LoanBook loanBook) throws  RemoteException;

  /**
   * Get the available books
   * @return
   * All the available books
   * @throws SQLException
   * @throws RemoteException
   */
  ArrayList<Book> getAvailableBooks() throws  RemoteException;

  /**
   * Get a user with a specific social security number
   * @param ssn
   * The social security number passed as an argument
   * @return
   * @throws RemoteException
   */
  LibraryUser getUser(String ssn) throws  RemoteException;

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

