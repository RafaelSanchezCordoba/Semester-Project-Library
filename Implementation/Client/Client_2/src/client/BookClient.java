package client;

import model.Book;

import java.io.Closeable;
import java.rmi.RemoteException;

/**
 * The client interface for book.
 * @author Maria Ortiz Planchuelo.
 * @version 1.0 09/04/22.
 */
public interface BookClient extends Closeable
{
  /**
   *
   * @param book
   * @throws RemoteException
   */
  void addBook(Book book) throws RemoteException;
  void removeBook(int id) throws RemoteException;

}
