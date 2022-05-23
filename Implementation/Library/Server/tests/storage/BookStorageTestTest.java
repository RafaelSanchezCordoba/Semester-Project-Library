package storage;

import model.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import persistance.storageTest.BookStorageTest;

import java.sql.SQLException;


public class BookStorageTestTest
{
  //check constrains of Db here ????????''



    private static BookStorageTest storage = BookStorageTest.getInstance();
    private static Book book ;
    private static Book book1;
    private static Book book2;

    @BeforeAll
    public static void  setupVariables(){
     book = new Book("mark of the shaper","atlas","hgfdsf",1999,2034);
     book1 = new Book("mark of the elder","atlas","polka",123,2011);
     book2 = new Book("hjkl","fghjk","saahara",23,203);
    }

    @Test
    public void checkEmpty() throws SQLException
    {
      storage.getBookList().clear();
      Assertions.assertTrue(storage.getBookList().isEmpty());
    }
    @Test
    public void addOne()
    {
      try
      {
        int exp = storage.getBookList().size();
        storage.addBook(book);
        exp++;
        Assertions.assertEquals(exp, storage.getBookList().size());
      }catch (SQLException e){
        e.printStackTrace();
      }
    }
    public  void clear() throws SQLException
    {
      storage.getBookList().clear();
    }
    @Test
    public void checkUniqueID() throws SQLException
    {
      //does not generate unique id fix
      clear();
    storage.addBook(book);
    storage.addBook(book1);
    storage.addBook(book2);
      System.out.println(storage.getBookList().get(1).getId());
    Assertions.assertNotEquals(storage.getBookList().get(1).getId(),storage.getBookList().get(2).getId());
    }

    @Test
  public void removeOne() throws SQLException
    {
      int exp = storage.getBookList().size();
      storage.addBook(book);
      storage.removeBook(book.getId());
      Assertions.assertEquals(exp,storage.getBookList().size());
    }
    @Test
  public void removeAndAddMultiple() throws SQLException
    {
      int exp = storage.getBookList().size();
      storage.addBook(book);
      storage.addBook(book);
      storage.addBook(book1);
      exp++;
      storage.removeBook(book.getId());
      storage.removeBook(book1.getId());

      Assertions.assertEquals(exp,storage.getBookList().size());
    }
    @Test
  public void removeFromEmpty() throws SQLException
    {
      int exp = storage.getBookList().size();
      storage.removeBook(4);
   Assertions.assertEquals(exp,storage.getBookList().size(),"-->no changes");
    }
    @Test
    public void removeNotExistingID() throws SQLException
    {
      int exp = storage.getBookList().size();
      storage.addBook(book2);
      exp++;
      storage.removeBook(999999999);
      Assertions.assertEquals(exp,storage.getBookList().size());
    }
  }

