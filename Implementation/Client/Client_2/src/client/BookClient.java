package client;

import model.Book;

import java.io.Closeable;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface BookClient extends Closeable
{

  void addBook(Book book) throws RemoteException;
  void removeBook(int id) throws RemoteException;
  ArrayList<Object[]>getBookList() throws RemoteException;

}
