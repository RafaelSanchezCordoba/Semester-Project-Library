package client;

import model.Book;
import model.Genre;
import model.GenreList;
import server.RemoteBook;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * The client implementation for book that extends <code>UnicastRemoteObject</code> and implements <code>BookClient</code>.
 * @author Maria Ortiz Planchuelo.
 * @version 1.0 09/04/22.
 */
public class BookClientImplementation extends UnicastRemoteObject implements  BookClient {

  private final  RemoteBook remoteBook;

  /**
   * The constructor that create and set the Registry.
   * @param host
   * The name of the host
   * @param port
   * The port number
   * @throws IOException
   * @throws NotBoundException
   */
  public BookClientImplementation(String  host, int port) throws IOException, NotBoundException {
    Registry registry = LocateRegistry.getRegistry(host,port);
    remoteBook = (RemoteBook) registry.lookup("book");

  }

  /**
   * Get book list method
   * @return
   * All the books in the list
   * @throws RemoteException
   * @throws SQLException
   */
  public ArrayList<Book> getBookList() throws RemoteException, SQLException {
    return  remoteBook.getBookList();
  }

  /**
   * Add book method
   * @param book
   * The book passed as an argument
   * @throws RemoteException
   * @throws SQLException
   */
  @Override public void addBook(Book book) throws RemoteException, SQLException {
    remoteBook.addBook(book);
  }

  /**
   * Remove a book with a specific id passed as an argument
   * @param id
   * The unique identification number
   * @throws RemoteException
   * @throws SQLException
   */
  @Override public void removeBook(int id) throws RemoteException, SQLException {
    remoteBook.removeBook(id);
  }

  /**
   * Get genre list method
   * @return
   * The list of genres
   * @throws RemoteException
   * @throws SQLException
   */
  @Override public GenreList getGenreList() throws RemoteException, SQLException {
    return remoteBook.getGenreList();
  }

  /**
   * Close the remote object by
   * unexporting unicast remote object
   * @throws IOException
   */
  @Override public void close() throws IOException {
      UnicastRemoteObject.unexportObject(this,true);
  }


}
