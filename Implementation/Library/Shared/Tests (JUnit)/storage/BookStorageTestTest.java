package storage;

import model.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import persistance.BookStorageTest;

import java.sql.SQLException;

public class BookStorageTestTest
{



    private static BookStorageTest storage = BookStorageTest.getInstance();
    private static Book book ;
    private static Book book1;
    private static Book book2;

    @BeforeAll
    public static void  setupVariables(){
      book = new Book("mark of the shaper","atlas");
      book1 = new Book("mark of the elder","atlas");
      book2 = new Book("fall of Oriath","spencers");
    }

    @Test
    public void addOne() throws SQLException
    {
      int exp =  storage.getBookList().size();
      storage.addBook(book);
      exp++;
      Assertions.assertEquals(exp,storage.getBookList().size());
    }
    public  void clear() throws SQLException
    {
      storage.getBookList().clear();
    }
    @Test
    public void checkUniqueID() throws SQLException
    {
      clear();
      int dif = book.getId();
      storage.addBook(book);
      storage.addBook(book1);
      storage.addBook(book2);
      storage.addBook(book);
      for (int i = 0; i<storage.getBookList().size();i++){
        System.out.println( storage.getBookList().get(i).toString());
      }
      //Assertions.assertNotEquals(dif,storage.getBookList().get(2).getId());
    }
  }

