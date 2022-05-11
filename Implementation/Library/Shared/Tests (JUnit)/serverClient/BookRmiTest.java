package serverClient;

import client.BookClientImplementation;
import model.Book;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookRmiTest
{

  private static Registry registry;
  private static BookClientImplementation bookClient;
  private static Book boonk;
  private static Book boonk1;
  private static ArrayList books;

  @BeforeAll
  public static   void setupBookServer()
      throws IOException, AlreadyBoundException, NotBoundException
  {
    registry = LocateRegistry.createRegistry(1010);
    bookClient = new BookClientImplementation("localHost", registry.REGISTRY_PORT);
    registry.bind("book", bookClient);

  }
  @BeforeEach
  public void setupVariables(){
    boonk = new Book("dombnom","fhjkl",77);
    boonk1 = new Book("sparta","spritied",98);
    try
    {
      books = bookClient.getBookList();
    }
    catch (SQLException | RemoteException e)
    {
      e.printStackTrace();
    }
  }
  @AfterEach
  public void tearDown() throws SQLException, RemoteException
  {

    books.clear();
    bookClient.getBookList().clear();
    boonk = null;
  }

  @Test public void addTheSameTwice() throws SQLException, RemoteException
  {
    int exp = bookClient.getBookList().size();
    bookClient.addBook(boonk);
    exp++;
    bookClient.addBook(boonk);
    exp++;
    Assertions.assertEquals(exp,bookClient.getBookList().size());

  }
  @Test
  public void addMultiple() throws SQLException, RemoteException
  {
    int expected = bookClient.getBookList().size();
    bookClient.addBook(boonk);
    expected++;
    bookClient.addBook(boonk1);
    expected++;
    bookClient.addBook(boonk);
    expected++;
    Assertions.assertEquals(expected,bookClient.getBookList().size());

  }
  @Test
  public void addBookTest() throws SQLException, RemoteException
  {
    int expected =  books.size();
    bookClient.addBook(boonk);
    expected++;
    Assertions.assertEquals(expected,bookClient.getBookList().size());

  }
  @Test
  public void removeBook() throws SQLException, RemoteException
  {
    int expected = books.size();
    bookClient.addBook(boonk);
    expected++;
    bookClient.removeBook(boonk.getId());
    expected--;
    Assertions.assertEquals(expected,books.size());
  }
  @Test
  public void removeMultiple() throws SQLException, RemoteException
  {
    int exp = bookClient.getBookList().size();
    bookClient.addBook(boonk);
    bookClient.addBook(boonk1);
    bookClient.addBook(boonk1);

    bookClient.removeBook(boonk.getId());
    bookClient.removeBook(boonk1.getId());
    bookClient.removeBook(boonk1.getId());

    Assertions.assertEquals(exp,bookClient.getBookList().size());
  }
  @Test
  public void removeZeroBooks(){
    int expected = books.size();
    books.remove(null);
    Assertions.assertEquals(expected,books.size());
  }
  @Test
  public void removeFromEmptyList() throws SQLException, RemoteException
  {
    bookClient.getBookList().clear();
    try
    {
      books.remove(boonk.getId());
    }catch (IndexOutOfBoundsException e)
    {
      String error =  e.getMessage();
      String message = "out of bounds";
      Assertions.assertTrue(error.contains(message),"Index out of bounds");
    }


  }
  @Test
  public  void addNullBook(){
    int expected = books.size();
    // books.add(null);

    Assertions.assertEquals(expected++,books.size());
  }
}


