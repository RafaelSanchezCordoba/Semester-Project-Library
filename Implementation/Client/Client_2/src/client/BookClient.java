package client;

import model.Book;

import java.io.Closeable;
import java.rmi.RemoteException;

public interface BookClient extends Closeable
{
  void addBook(Book book) throws RemoteException;
  void removeBook(int id) throws RemoteException;

}
