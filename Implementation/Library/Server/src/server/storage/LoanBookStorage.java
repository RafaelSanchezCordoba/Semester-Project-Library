package server.storage;

import model.Book;
import model.LoanBook;

import java.sql.SQLException;
import java.util.ArrayList;

public interface LoanBookStorage
{
  void addLoanBook(LoanBook loanBook) throws SQLException;
  ArrayList<Book> getAvailableBooks() throws SQLException;
}
