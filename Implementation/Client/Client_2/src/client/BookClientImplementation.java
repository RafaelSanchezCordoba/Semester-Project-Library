package client;

import dk.via.remote.observer.RemotePropertyChangeEvent;
import dk.via.remote.observer.RemotePropertyChangeListener;
import model.Book;
import model.MultimediaItem;
import server.RemoteBook;


import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class BookClientImplementation extends UnicastRemoteObject implements  BookClient, RemotePropertyChangeListener<Book> {


  public BookClientImplementation(String  host, int port) throws IOException,
      NotBoundException
  {


  }
  @Override public void addBook(Book book)
  {

  }

  @Override public void removeBook(int id)
  {

  }



  @Override public void close() throws IOException
  {

  }

  @Override
  public void propertyChange(RemotePropertyChangeEvent<Book> remotePropertyChangeEvent) throws RemoteException {

  }
}
