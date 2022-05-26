package server;

import dk.via.remote.observer.RemotePropertyChangeListener;
import model.Book;
import model.LibraryUser;
import model.LoanBook;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface RemoteLoanBook extends Remote
{
  void addBookLoan(LoanBook loanBook) throws  RemoteException;
  ArrayList<Book> getAvailableBookList() throws  RemoteException;
  LibraryUser getUser(String ssn) throws RemoteException;
  ArrayList<LoanBook> getUserBookLoans(String ssn) throws RemoteException;
  void returnBook(int loan_id) throws RemoteException;

  void addPropertyChangeListener(RemotePropertyChangeListener<ArrayList<Book>> listener) throws RemoteException;
  void removePropertyChangeListener(RemotePropertyChangeListener<ArrayList<Book>> listener) throws RemoteException;

}

