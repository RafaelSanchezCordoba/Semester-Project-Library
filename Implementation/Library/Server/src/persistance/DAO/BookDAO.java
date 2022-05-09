package persistance.DAO;

import model.Book;

import java.sql.SQLException;
import java.util.ArrayList;

public interface BookDAO {
  void removeBook(int id) throws SQLException;
  ArrayList<Book> getBookList() throws SQLException;
  void addBook(Book book) throws SQLException;

}
