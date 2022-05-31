package storage;

import model.Librarian;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import storageTest.LibrarianStorageTest;
import server.storage.LibrarianStorage;

import java.rmi.RemoteException;
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
  public static void setupVariables() throws RemoteException
  {
    librarian = new Librarian("1234","pass","marco","polo");
    librarian1 = new Librarian("2345", "pass","cristoforo","colombo");
    librarian2 = new Librarian("3456","pass","stefano","magellano");
    expected = storage.getLibrarianList().size();
  }

  @Test
  public  void addOne() throws RemoteException
  {
    storage.addLibrarian(librarian);
    expected++;
    Assertions.assertEquals(expected,storage.getLibrarianList().size());
  }

  @Test
  public void checkAttributesOfAddedLibrarian() throws RemoteException
  {
    storage.addLibrarian(librarian);
    Assertions.assertEquals(librarian,storage.getLibrarianList().get(0));
  }

  @Test
  public void addMultiple() throws RemoteException
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
  public void removeOne() throws RemoteException
  {
    storage.addLibrarian(librarian);
    expected++;
    storage.addLibrarian(librarian2);
    expected++;
    storage.removeLibrarian(librarian.getSsn());

    Assertions.assertEquals(expected,storage.getLibrarianList().size());
  }
  @Test
  public void removeMultiple() throws RemoteException
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
  public void addWithSameSsn() throws RemoteException
  {
    storage.addLibrarian(librarian2);
    expected++;
    storage.addLibrarian(librarian2);
    expected++;

    Assertions.assertFalse(expected!=storage.getLibrarianList().size());
  }

  @Test
  public void removeNonExistingSsn() throws RemoteException
  {
    storage.addLibrarian(librarian);
    expected++;

    storage.removeLibrarian(librarian2.getSsn());
    //no changes

    Assertions.assertEquals(expected,storage.getLibrarianList().size());
  }
}
