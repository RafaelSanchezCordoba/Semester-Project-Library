package persistance.storageTest;

import model.Book;
import server.storage.BookStorage;

import java.sql.SQLException;
import java.util.ArrayList;

public class BookStorageTest implements BookStorage
{
  private int counter = 0;
  private ArrayList<Book> books;
  private static BookStorageTest instance;

  private BookStorageTest()
  {
    this.books = new ArrayList<Book>();
  }
  public static synchronized BookStorageTest getInstance(){
    if(instance == null){
      instance = new BookStorageTest();
    }
    return instance;
  }

  @Override public void removeBook(int id) throws SQLException
  {
    for (int i = 0; i <books.size() ; i++)
    {
      if(id == books.get(i).getId()){
        books.remove(books.get(i));
        break;
      }
    }
  }

  @Override public ArrayList<Book> getBookList() throws SQLException
  {
    return books;
  }

  @Override public void addBook(Book book) throws SQLException
  {
    books.add(book);
    book.setId(counter);
    counter++;
    System.out.println(book);
  }
}
