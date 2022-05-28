package persistance.DAO;

import model.Book;
import model.Genre;
import model.GenreList;

import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Public BookDAO interface that is implemented by
 * BookDAOImplementation class
 * The purpose of this class is to provide abstract
 * interface for the database.
 */
public interface BookDAO {

  /**
   * Method removing book after it's id
   * @param id
   * Id of the book
   * @throws SQLException
   */
  void removeBook(int id) throws SQLException;

  /**
   * Method returning list of the books as ArrayList
   * @return
   * List of the books as ArrayList
   * @throws SQLException
   */
  ArrayList<Book> getBookList() throws SQLException;

  /**
   * Method returning list of genres as
   * ArrayList from GenreList class
   * @return
   * List of genres as ArrayList
   * @throws SQLException
   */
  GenreList getGenreList() throws SQLException;

  /**
   * Method adding a book
   * @param book
   * Book
   * @throws SQLException
   * SQL exception with the message "no keys generated" can be caught
   * from BookDAOImplementation class
   */
  void addBook(Book book) throws SQLException;

}
