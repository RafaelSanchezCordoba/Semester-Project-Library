package client;

import model.Book;

import java.io.Closeable;

public interface BookClient extends Closeable
{
  void addBook(Book book);
  void removeBook(int id);

}
