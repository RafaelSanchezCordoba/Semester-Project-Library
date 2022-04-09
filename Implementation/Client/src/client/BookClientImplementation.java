package client;

import model.multimediaItem.Book;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.server.UnicastRemoteObject;

public class BookClientImplementation extends UnicastRemoteObject implements  BookClient
{
  private final RemoteBook

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

  @Override public Book getBook()
  {
    return null;
  }

  @Override public void close() throws IOException
  {

  }
}
