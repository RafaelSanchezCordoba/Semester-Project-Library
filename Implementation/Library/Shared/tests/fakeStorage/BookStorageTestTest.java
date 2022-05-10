package fakeStorage;

import model.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistance.storageTest.BookStorageTest;
import server.storage.BookStorage;

import java.rmi.RemoteException;
import java.sql.SQLException;

public class BookStorageTestTest
{
 private static BookStorage storage = BookStorageTest.getInstance();
 private static Book book ;
 private static Book book1;
 private static Book book2;

 @BeforeAll
  public static void  setupVariables(){
   book = new Book(100,"mark of the shaper","atlas");
   book1 = new Book(100,"mark of the elder","atlas");
   book2 = new Book(100,"fall of Oriath","spencers");
 }

 @Test
  public void addOne() throws SQLException, RemoteException
 {
 int exp =  storage.getBookList().size();
   storage.addBook(book);
   exp++;
   Assertions.assertEquals(exp,storage.getBookList().size());
 }
 public  void clear() throws SQLException, RemoteException
 {
  storage.getBookList().clear();
 }
 @Test
 public void checkUniqueID() throws SQLException, RemoteException
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
