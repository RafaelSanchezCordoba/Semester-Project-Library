package persistance.adapters;

import model.Book;
import model.LibraryUser;
import model.LoanBook;
import persistance.DAO.LoanBookDAO;
import server.storage.LoanBookStorage;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Public class AdapterLoanBookDAO implementing LoanBookStorage interface
 * This adapter's purpose is to minimize the direct dependency between
 * application code and data access code.
 */
public class AdapterLoanBookDAO implements LoanBookStorage
{

  private LoanBookDAO loanBookDAO;

  /**
   * AdapterLoanBookDAO one parameter constructor.
   * @param loanBookDAO
   * Parameter from interface LoanBookDAO
   */
  public AdapterLoanBookDAO(LoanBookDAO loanBookDAO){
    this.loanBookDAO = loanBookDAO;
  }

  /**
   * Method adding a book to loan
   * @param loanBook
   * LoanBook
   * @throws RemoteException
   * SQL exception with the message "No keys generated" can be caught
   * from LoanBookDAOImplementation class
   */
  @Override public void addLoanBook(LoanBook loanBook) throws RemoteException
  {
    try
    {
      loanBookDAO.addLoanBook(loanBook);
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
  }

  /**
   * Method returning ArrayList of all books available to loan
   * @return
   * ArrayList of books available to loan
   * @throws RemoteException
   */
  @Override public ArrayList<Book> getAvailableBooks() throws RemoteException
  {
    try
    {
      return loanBookDAO.getAvailableBooks();
    }
    catch (SQLException e)
    {

      e.printStackTrace();
    }
    return null;
  }

  /**
   * Method returning a user after it's ssn
   * @param ssn
   * Social security number
   * @return
   * User with given ssn number
   * @throws RemoteException
   */
  @Override public LibraryUser getUser(String ssn) throws RemoteException
  {
    try
    {
      return loanBookDAO.getUser(ssn);
    }catch (SQLException e){
      throw  new RemoteException(e.getMessage());
    }
  }

  /**
   * Method returning loans of user after it's ssn
   * @param ssn
   * Social security number
   * @return
   * Loans of user after it's ssn
   * @throws RemoteException
   */
  @Override public ArrayList<LoanBook> getUserBookLoans(String ssn)
      throws RemoteException
  {
    try
    {
      return loanBookDAO.getUserBookLoans(ssn);
    }catch (SQLException e){
      throw new RemoteException(e.getMessage());
    }
  }

  /**
   * Method returning a book from being loaned to the library
   * @param loan_id
   * ID number of loan
   * @throws RemoteException
   */
  @Override public void returnBook(int loan_id) throws RemoteException
  {
    try
    {
      loanBookDAO.returnBook(loan_id);
    }catch (SQLException e){
      throw new RemoteException(e.getMessage());
    }
  }
}
