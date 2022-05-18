package mediator;

import client.LoanBookClient;
import model.Book;
import model.LibraryUser;
import model.LoanBook;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public class ModelManagerLoanBook implements ModelLoanBook
{
  private final LoanBookClient client;
  private PropertyChangeSupport support;

  public ModelManagerLoanBook(LoanBookClient loanBookClient){
    this.client = loanBookClient;
    this.support = new PropertyChangeSupport(this);
  }

  @Override public void addLoanBook(LoanBook loanBook)
      throws SQLException, RemoteException
  {
    client.addBookLoan(loanBook);
    support.firePropertyChange("newLoanBook",null,loanBook);
  }

  @Override public ArrayList<Book> getAvailableBooks()
      throws SQLException, RemoteException
  {
    return client.getAvailableBookList();
  }

  @Override public LibraryUser getUser(String ssn)
      throws RemoteException
  {
   return client.getUser(ssn);
  }

  @Override public void addPropertyChangeListener(
      PropertyChangeListener listener)
  {

  }

  @Override public void addPropertyChangeListener(String name,
      PropertyChangeListener listener)
  {

  }

  @Override public void removePropertyChangeListener(
      PropertyChangeListener listener)
  {

  }

  @Override public void removePropertyChangeListener(String name,
      PropertyChangeListener listener)
  {

  }
}
