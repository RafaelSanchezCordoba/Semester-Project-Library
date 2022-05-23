package client;

import model.Book;
import model.LibraryUser;
import model.LoanBook;


import java.io.Closeable;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface LoanBookClient extends Closeable
{
  void addBookLoan(LoanBook loanBook) throws SQLException, RemoteException;
  ArrayList<Book> getAvailableBookList() throws SQLException, RemoteException;
  LibraryUser getUser(String ssn) throws RemoteException;
  ArrayList<LoanBook> getUserBookLoans(String ssn) throws RemoteException;
  void returnBook(int loan_id) throws RemoteException;
}
