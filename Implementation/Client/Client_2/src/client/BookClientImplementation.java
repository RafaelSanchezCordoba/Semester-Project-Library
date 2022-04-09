package client;


import model.Book;
import server.RemoteBook;


import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class BookClientImplementation extends UnicastRemoteObject implements  BookClient {

  private final  RemoteBook remoteBook;
  public BookClientImplementation(String  host, int port) throws IOException,
      NotBoundException
  {
    Registry registry = LocateRegistry.getRegistry();
    remoteBook = (RemoteBook) registry.lookup("book");

  }
  @Override public void addBook(Book book) throws RemoteException
  {
    remoteBook.addBook(book);
  }

  @Override public void removeBook(int id) throws RemoteException
  {
    remoteBook.removeBook(id);
  }



  @Override public void close() throws IOException
  {
      UnicastRemoteObject.unexportObject(this,true);
  }


}
