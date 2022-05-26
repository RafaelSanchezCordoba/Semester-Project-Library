package server.storage;

import model.Book;
import model.Genre;
import model.GenreList;
import model.Magazine;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Public BookStorage interface that is
 * implemented by AdapterBookDAO class
 * The purpose of this class is to provide abstract
 * interface with all methods for class AdapterBookDAO
 */
public interface BookStorage
{

  /**
   * Method adding a book
   * @param book
   * Book
   * @throws RemoteException
   * SQL exception with the message "no keys generated" can be caught
   * from BookDAOImplementation
   */
  void addBook(Book book) throws  RemoteException;

  /**
   * Method removing book after it's id
   * @param id
   * Id of the book
   * @throws RemoteException
   */
  void removeBook(int id) throws  RemoteException;

  /**
   * Method returning list of the books as ArrayList
   * @return
   * List of the books as ArrayList
   * @throws RemoteException
   */
  ArrayList<Book> getBookList() throws  RemoteException;

  /**
   * Method returning list of genres as
   * ArrayList from GenreList class
   * @return
   * List of genres as ArrayList
   * @throws RemoteException
   */
  GenreList getGenreList() throws  RemoteException;
}
