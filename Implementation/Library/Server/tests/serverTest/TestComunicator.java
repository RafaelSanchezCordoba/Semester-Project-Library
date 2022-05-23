package serverTest;

import model.*;
import server.RemoteLoanBook;
import server.RemoteLoanMagazine;
import server.storage.LoanBookStorage;
import server.storage.LoanMagazineStorage;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;

import java.rmi.server.UnicastRemoteObject;

public class TestComunicator extends UnicastRemoteObject implements RemoteLoanBook, RemoteLoanMagazine
{
  private LoanMagazineStorage loanMagazineStorage;
  private LoanBookStorage loanBookStorage;

  public TestComunicator(LoanBookStorage loanBookStorage, LoanMagazineStorage loanMagazineStorage)throws RemoteException {
    this.loanBookStorage = loanBookStorage;
    this.loanMagazineStorage = loanMagazineStorage;
  }

  @Override public void addBookLoan(LoanBook loanBook)
      throws SQLException, RemoteException
  {
    loanBookStorage.addLoanBook(loanBook);
  }

  @Override public ArrayList<Book> getAvailableBookList()
      throws SQLException, RemoteException
  {
    return   loanBookStorage.getAvailableBooks();
  }

  @Override public void addMagazineLoan(LoanMagazine loanMagazine)
      throws RemoteException, SQLException
  {
    loanMagazineStorage.addMagazineLoan(loanMagazine);
  }

  @Override public ArrayList<Magazine> getAvailableMagazineList()
      throws RemoteException, SQLException
  {
    return loanMagazineStorage.getAvailableMagazineList();
  }

  @Override public LibraryUser getUser(String ssn) throws RemoteException
  {
    return loanBookStorage.getUser(ssn);
  }

  @Override public ArrayList<LoanMagazine> getUserLoans(String ssn)
      throws RemoteException
  {
    return null;
  }

  @Override public void returnMagazine(int loan_id) throws RemoteException
  {

  }

  @Override public ArrayList<LoanBook> getUserBookLoans(String ssn)
      throws RemoteException
  {
    return null;
  }

  @Override public void returnBook(int loan_id) throws RemoteException
  {

  }
}
