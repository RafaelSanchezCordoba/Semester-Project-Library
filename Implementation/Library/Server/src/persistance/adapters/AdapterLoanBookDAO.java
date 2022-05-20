package persistance.adapters;

import model.Book;
import model.LibraryUser;
import model.LoanBook;
import persistance.DAO.LoanBookDAO;
import server.storage.LoanBookStorage;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdapterLoanBookDAO implements LoanBookStorage
{

  private LoanBookDAO loanBookDAO;

  public AdapterLoanBookDAO(LoanBookDAO loanBookDAO){
    this.loanBookDAO = loanBookDAO;
  }
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

  @Override public LibraryUser getUser(String ssn) throws RemoteException
  {
    try
    {
      return loanBookDAO.getUser(ssn);
    }catch (SQLException e){
      throw  new RemoteException(e.getMessage());
    }
  }

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
