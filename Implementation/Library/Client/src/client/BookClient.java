package client;

import model.Book;
import model.Genre;
import model.GenreList;

import java.io.Closeable;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * The client interface for book.
 * @author Maria Ortiz Planchuelo.
 * @version 1.0 09/04/22.
 */
public interface BookClient extends Closeable {
  /**
   * Add book method
   * @param book
   * The book passed as an argument
   * @throws RemoteException
   * @throws SQLException
   */
  void addBook(Book book) throws RemoteException, SQLException;

  /**
   * Remove a book with a specific id passed as an argument
   * @param id
   * The unique identification number
   * @throws RemoteException
   * @throws SQLException
   */
  void removeBook(int id) throws RemoteException, SQLException;

  /**
   * Get book list method
   * @return
   * All the books in the list as a String
   * @throws RemoteException
   * @throws SQLException
   */
  ArrayList<Book> getBookList() throws RemoteException, SQLException;

  /**
   * Get genre list method
   * @return
   * All the genres in the list as a String
   * @throws RemoteException
   * @throws SQLException
   */
  GenreList getGenreList() throws RemoteException, SQLException;

}
