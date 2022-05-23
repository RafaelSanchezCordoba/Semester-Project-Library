package client;

import model.Book;
import model.LibraryUser;
import model.LoanBook;
import server.RemoteLoanBook;
import server.RemoteLoanMagazine;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;

public class LoanBookClientImplementation extends UnicastRemoteObject implements LoanBookClient
{
  private RemoteLoanBook remoteLoanBook;

  public LoanBookClientImplementation(String host, int port) throws IOException,
      NotBoundException
  {
    Registry registry = LocateRegistry.getRegistry(host,port);
    remoteLoanBook = (RemoteLoanBook) registry.lookup("loanBook");
  }

  @Override public void addBookLoan(LoanBook loanBook)
      throws SQLException, RemoteException
  {
    remoteLoanBook.addBookLoan(loanBook);
  }

  @Override public ArrayList<Book> getAvailableBookList()
      throws SQLException, RemoteException
  {
    return remoteLoanBook.getAvailableBookList();
  }

  @Override public LibraryUser getUser(String ssn)
      throws RemoteException
  {
     return  remoteLoanBook.getUser(ssn);
  }

  @Override public ArrayList<LoanBook> getUserBookLoans(String ssn)
      throws RemoteException
  {
    return remoteLoanBook.getUserBookLoans(ssn);
  }

  @Override public void returnBook(int loan_id) throws RemoteException
  {
    remoteLoanBook.returnBook(loan_id);
  }

  @Override public void close() throws IOException
  {
    UnicastRemoteObject.unexportObject(this,true);
  }
}
