package client;

import model.Book;
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
  public BookClientImplementation(String  host, int port) throws IOException,
      NotBoundException
  {
    Registry registry = LocateRegistry.getRegistry(host,port);
    remoteBook = (RemoteBook) registry.lookup("book");

  }
  public ArrayList<Book> getBookList() throws RemoteException, SQLException
  {
    return  remoteBook.getBookList();
  }
  @Override public void addBook(Book book) throws RemoteException, SQLException
  {
    remoteBook.addBook(book);
  }

  @Override public void removeBook(int id) throws RemoteException, SQLException
  {
    remoteBook.removeBook(id);
  }



  @Override public void close() throws IOException
  {
      UnicastRemoteObject.unexportObject(this,true);
  }


}
