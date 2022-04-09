
import sharedObjects.Book;

import java.rmi.Remote;

public interface BookRemote extends Remote
{
  void addBook( Book book);
  void removeBook(int id);
  Book getBook();
}
