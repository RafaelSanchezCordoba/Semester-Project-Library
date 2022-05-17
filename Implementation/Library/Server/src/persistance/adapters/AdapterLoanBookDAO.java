package persistance.adapters;

import model.Book;
import model.LoanBook;
import persistance.DAO.LoanBookDAO;
import server.storage.LoanBookStorage;

import java.sql.SQLException;
import java.util.ArrayList;

public class AdapterLoanBookDAO implements LoanBookStorage
{

  private LoanBookDAO loanBookDAO;

  public AdapterLoanBookDAO(LoanBookDAO loanBookDAO){
    this.loanBookDAO = loanBookDAO;
  }
  @Override public void addLoanBook(LoanBook loanBook) throws SQLException
  {
    loanBookDAO.addLoanBook(loanBook);
  }

  @Override public ArrayList<Book> getAvailableBooks() throws SQLException
  {
    return loanBookDAO.getAvailableBooks();
  }
}
