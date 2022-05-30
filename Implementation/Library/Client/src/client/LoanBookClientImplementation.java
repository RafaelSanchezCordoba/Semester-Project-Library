package client;

import dk.via.remote.observer.RemotePropertyChangeEvent;
import dk.via.remote.observer.RemotePropertyChangeListener;
import model.Book;
import model.LibraryUser;
import model.LoanBook;
import server.RemoteLoanBook;
import server.RemoteLoanMagazine;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;

public class LoanBookClientImplementation extends UnicastRemoteObject implements LoanBookClient, RemotePropertyChangeListener<LoanBook>
{
  private RemoteLoanBook remoteLoanBook;
  private final PropertyChangeSupport support;

  /**
   * The constructor that create and set the Registry.
   * @param host
   * The name of the host
   * @param port
   * The port number
   * @throws IOException
   * @throws NotBoundException
   */
  public LoanBookClientImplementation(String host, int port) throws IOException, NotBoundException {
    Registry registry = LocateRegistry.getRegistry(host,port);
    remoteLoanBook = (RemoteLoanBook) registry.lookup("loanBook");
    support = new PropertyChangeSupport(this);
    remoteLoanBook.addPropertyChangeListener("addLoanBook", this);
  }

  /**
   * Add loan book method
   * @param loanBook
   * The loan book passed as an argument
   * @throws SQLException
   * @throws RemoteException
   */
  @Override public void addBookLoan(LoanBook loanBook)
      throws  RemoteException
  {
    remoteLoanBook.addBookLoan(loanBook);
  }

  /**
   * Get the available books
   * @return
   * All the available books
   * @throws SQLException
   * @throws RemoteException
   */
  @Override public ArrayList<Book> getAvailableBookList()
      throws  RemoteException
  {
    return remoteLoanBook.getAvailableBookList();
  }

  /**
   * Get a user with a specific social security number
   * @param ssn
   * The social security number passed as an argument
   * @return
   * @throws RemoteException
   */
  @Override public LibraryUser getUser(String ssn)
      throws RemoteException
  {
     return  remoteLoanBook.getUser(ssn);
  }

  /**
   * Get the loan of the user with the social security number passed as an argument
   * @param ssn
   * The social security number
   * @return
   * @throws RemoteException
   */
  @Override public ArrayList<LoanBook> getUserBookLoans(String ssn)
      throws RemoteException
  {
    return remoteLoanBook.getUserBookLoans(ssn);
  }

  /**
   * Return book method
   * @param loan_id
   * The unique identification number of the loan
   * @throws RemoteException
   */
  @Override public void returnBook(int loan_id) throws RemoteException
  {
    remoteLoanBook.returnBook(loan_id);
  }

  @Override
  public void addPropertyChangeListener(PropertyChangeListener listener) {
    support.addPropertyChangeListener(listener);
  }

  @Override
  public void removePropertyChangeListener(PropertyChangeListener listener) {
    support.removePropertyChangeListener(listener);
  }

  /**
   * Close the remote object
   * @throws IOException
   */
  @Override public void close() throws IOException
  {
    UnicastRemoteObject.unexportObject(this,true);
  }

  @Override
  public void propertyChange(RemotePropertyChangeEvent<LoanBook> event) throws RemoteException {
    support.firePropertyChange(event.getPropertyName(), event.getOldValue(), event.getNewValue());
  }
}
