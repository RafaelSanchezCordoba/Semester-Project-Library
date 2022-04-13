package client;

import model.Book;

import java.io.Closeable;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * The client interface for book.
 * @author Maria Ortiz Planchuelo.
 * @version 1.0 09/04/22.
 */
public interface BookClient extends Closeable
{
<<<<<<< HEAD

=======
  /**
   *
   * @param book
   * @throws RemoteException
   */
>>>>>>> 140f6a0c353514d0b2e9badd881b8031c2386050
  void addBook(Book book) throws RemoteException;
  void removeBook(int id) throws RemoteException;
  ArrayList<Object[]>getBookList() throws RemoteException;

}
