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
public interface BookClient extends Closeable
{

  /**
  *addbook method
   */
  void addBook(Book book) throws RemoteException, SQLException;
  void removeBook(int id) throws RemoteException, SQLException;
  ArrayList<Book> getBookList() throws RemoteException, SQLException;
  GenreList getGenreList() throws RemoteException, SQLException;

}
