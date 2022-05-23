package server.storage;

import model.Book;
import model.LibraryUser;
import model.LoanBook;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface LoanBookStorage
{
  void addLoanBook(LoanBook loanBook) throws SQLException, RemoteException;
  ArrayList<Book> getAvailableBooks() throws SQLException, RemoteException;
  LibraryUser getUser(String ssn) throws RemoteException;
  ArrayList<LoanBook> getUserBookLoans(String ssn) throws RemoteException;
  void returnBook(int loan_id) throws RemoteException;
}
