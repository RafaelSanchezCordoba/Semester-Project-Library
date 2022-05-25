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

  /**
   * Public constructor that set the client and a property change support
   * @param loanBookClient
   * The loan book client
   */
  public ModelManagerLoanBook(LoanBookClient loanBookClient){
    this.client = loanBookClient;
    this.support = new PropertyChangeSupport(this);
  }

  /**
   * Add loan book method
   * @param loanBook
   * The loan book passed as an argument
   * @throws SQLException
   * @throws RemoteException
   */
  @Override public void addLoanBook(LoanBook loanBook)
      throws  RemoteException
  {
    client.addBookLoan(loanBook);
    support.firePropertyChange("newLoanBook",null,loanBook);
  }

  /**
   * Get the available books
   * @return
   * All the available books
   * @throws SQLException
   * @throws RemoteException
   */
  @Override public ArrayList<Book> getAvailableBooks()
      throws  RemoteException
  {
    return client.getAvailableBookList();
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
   return client.getUser(ssn);
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
    return client.getUserBookLoans(ssn);
  }

  /**
   * Return book method
   * @param loan_id
   * The unique identification number of the loan
   * @throws RemoteException
   */
  @Override public void returnBook(int loan_id) throws RemoteException
  {
    client.returnBook(loan_id);
    support.firePropertyChange("removeLoanBook",null,loan_id);
  }

  /**
   * Add property change listener, just with the listener
   * @param listener
   * The listener
   */
  @Override public void addPropertyChangeListener(
      PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(listener);
  }

  /**
   * Add property change listener with a name and the listener
   * @param name
   * The name
   * @param listener
   * The listener
   */
  @Override public void addPropertyChangeListener(String name,
      PropertyChangeListener listener)
  {
      support.addPropertyChangeListener(name,listener);
  }

  /**
   * Remove property change listener, just with the listener
   * @param listener
   * The listener
   */
  @Override public void removePropertyChangeListener(
      PropertyChangeListener listener)
  {
    support.removePropertyChangeListener(listener);
  }

  /**
   * Remove property change listener with a name and the listener
   * @param name
   * The name
   * @param listener
   * The listener
   */
  @Override public void removePropertyChangeListener(String name,
      PropertyChangeListener listener)
  {
    support.removePropertyChangeListener(name,listener);
  }
}
