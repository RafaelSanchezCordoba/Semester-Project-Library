package persistance.DAO;

import model.Book;
import model.LibraryUser;
import model.LoanBook;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Public LoanBookDAO interface that is implemented by
 * LoanBookDAOImplementation class
 * The purpose of this class is to provide abstract
 * interface with all method for class AdapterLoanBookDAO
 */
public interface LoanBookDAO
{
  /**
   * Method adding a book to loan
   * @param loanBook
   * LoanBook
   * @throws SQLException
   * SQL exception with message "No keys generated" can be caught
   * from LoanBookDAOImplementation class
   */
  void addLoanBook(LoanBook loanBook) throws SQLException;

  /**
   * Method returning ArrayList of all books available to loan
   * @return
   * ArrayList of books available to loan
   * @throws SQLException
   */
  ArrayList<Book> getAvailableBooks() throws SQLException;

  /**
   * Method returning a user after it's snn
   * @param ssn
   * Social security number
   * @return
   * User with given snn number
   * @throws SQLException
   */
  LibraryUser getUser(String ssn) throws SQLException;

  /**
   * Method returning loans of user after it's ssn
   * @param ssn
   * Social security number
   * @return
   * Loans of user after it's ssn
   * @throws SQLException
   */
  ArrayList<LoanBook> getUserBookLoans(String ssn) throws SQLException;

  /**
   * Method returning a book from being loaned to the library
   * @param loan_id
   * ID number of loan
   * @throws SQLException
   */
  void returnBook(int loan_id) throws SQLException;
}
