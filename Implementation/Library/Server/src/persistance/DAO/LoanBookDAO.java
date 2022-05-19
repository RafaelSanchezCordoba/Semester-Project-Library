package persistance.DAO;

import model.Book;
import model.LibraryUser;
import model.LoanBook;

import java.sql.SQLException;
import java.util.ArrayList;

public interface LoanBookDAO
{
  void addLoanBook(LoanBook loanBook) throws SQLException;
  ArrayList<Book> getAvailableBooks() throws SQLException;
  LibraryUser getUser(String ssn) throws SQLException;
}
