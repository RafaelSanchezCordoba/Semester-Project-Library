package client;

import model.multimediaItem.Book;

import java.io.Closeable;

public interface BookClient extends Closeable
{
  void addBook(Book book);
  void removeBook(int id);
  Book  getBook();
}
