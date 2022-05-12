package storage;

import model.Librarian;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import persistance.storageTest.LibrarianStorageTest;
import server.storage.LibrarianStorage;

import java.sql.SQLException;

public class LibrarianStorageTestTest
{
  private static LibrarianStorage storage;

  private static Librarian librarian;
  private static Librarian librarian1;
  private static Librarian librarian2;
  private static int expected;
  @BeforeAll
  public static void setup(){
    storage = LibrarianStorageTest.getInstance();
  }

  @BeforeAll
  public static void setupVariables() throws SQLException
  {
    librarian = new Librarian("1245","pass","marco","polo");
    librarian1 = new Librarian("2345", "pass","cristoforo","colombo");
    librarian2 = new Librarian("3456","pass","stefano","magellano");
    expected = storage.getLibrarianList().size();
  }

  @Test
  public  void addOne() throws SQLException
  {
    storage.addLibrarian(librarian);
    expected++;
    Assertions.assertEquals(expected,storage.getLibrarianList().size());
  }

  @Test
  public void checkAttributesOfAddedLibrarian() throws SQLException
  {
    storage.addLibrarian(librarian);
    Assertions.assertEquals(librarian,storage.getLibrarianList().get(0));
  }

  @Test
  public void addMultiple() throws SQLException
  {
    storage.addLibrarian(librarian);
    expected++;
    storage.addLibrarian(librarian1);
    expected++;
    storage.addLibrarian(librarian2);
    expected++;

    Assertions.assertEquals(expected,storage.getLibrarianList().size());
  }
  @Test
  public void removeOne() throws SQLException
  {
    storage.addLibrarian(librarian);
    storage.addLibrarian(librarian2);
    expected++;
    storage.removeLibrarian(librarian.getSsn());

    Assertions.assertEquals(expected,storage.getLibrarianList().size());
  }
  @Test
  public void removeMultiple() throws SQLException
  {
    storage.addLibrarian(librarian);
    expected++;
    storage.addLibrarian(librarian1);
    expected++;
    storage.addLibrarian(librarian2);
    expected++;

    storage.removeLibrarian(librarian.getSsn());
    expected--;
    storage.removeLibrarian(librarian1.getSsn());
    expected--;

    Assertions.assertEquals(expected,storage.getLibrarianList().size());
  }

  @Test
  public void addWithSameSsn() throws SQLException
  {
    storage.addLibrarian(librarian2);
    expected++;
    storage.addLibrarian(librarian2);
    expected++;

    Assertions.assertTrue(expected!=storage.getLibrarianList().size());
  }

 @Test
  public void removeNonExistingSsn() throws SQLException
 {
    storage.addLibrarian(librarian);
    expected++;

    storage.removeLibrarian(librarian2.getSsn());
    //no changes

    Assertions.assertEquals(expected,storage.getLibrarianList().size());
 }
}
