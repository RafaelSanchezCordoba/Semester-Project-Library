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
      throws  RemoteException
  {
    client.addBookLoan(loanBook);
    support.firePropertyChange("newLoanBook",null,loanBook);
  }

  @Override public ArrayList<Book> getAvailableBooks()
      throws  RemoteException
  {
    return client.getAvailableBookList();
  }

  @Override public LibraryUser getUser(String ssn)
      throws RemoteException
  {
   return client.getUser(ssn);
  }

  @Override public ArrayList<LoanBook> getUserBookLoans(String ssn)
      throws RemoteException
  {
    return client.getUserBookLoans(ssn);
  }

  @Override public void returnBook(int loan_id) throws RemoteException
  {
    client.returnBook(loan_id);
    support.firePropertyChange("removeLoanBook",null,loan_id);
  }

  @Override public void addPropertyChangeListener(
      PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(listener);
  }

  @Override public void addPropertyChangeListener(String name,
      PropertyChangeListener listener)
  {
      support.addPropertyChangeListener(name,listener);
  }

  @Override public void removePropertyChangeListener(
      PropertyChangeListener listener)
  {
    support.removePropertyChangeListener(listener);
  }

  @Override public void removePropertyChangeListener(String name,
      PropertyChangeListener listener)
  {
    support.removePropertyChangeListener(name,listener);
  }
}
