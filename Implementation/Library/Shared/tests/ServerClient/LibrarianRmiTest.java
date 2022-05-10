package ServerClient;

import client.LibrarianClient;
import client.LibrarianClientImplementation;
import model.Librarian;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class LibrarianRmiTest
{
  private static Registry registry;
  private static LibrarianClientImplementation librarianClient;
  private static Librarian librarian;
  private static Librarian librarian1;
  private static Librarian librarian2;

  @BeforeAll
  public static void setupLibrarianServer()
      throws IOException, NotBoundException, AlreadyBoundException
  {
    registry = LocateRegistry.createRegistry(1011);
    librarianClient = new LibrarianClientImplementation("localhost",Registry.REGISTRY_PORT);
    registry.bind("librarian",librarianClient);
  }

  @BeforeAll
  public static void setupVariables(){
    librarian = new Librarian(1,"form","abe","smith");
    librarian1 = new Librarian(2,"pass","norm","dwaine");
    librarian2 = new Librarian(3,"smoke","peter","minus");
  }
  @Test
  public void addNull() throws SQLException, RemoteException
  {
    int exp = librarianClient.getLibrarianList().size();
  // librarianClient.addLibrarian(null);


    Assertions.assertEquals(exp,librarianClient.getLibrarianList().size());
  }
  @Test
  public void addOne() throws SQLException, RemoteException
  {
    int exp = librarianClient.getLibrarianList().size();
    librarianClient.addLibrarian(librarian);
    exp++;
    Assertions.assertEquals(exp,librarianClient.getLibrarianList().size());
  }
  @Test
  public void removeOne() throws SQLException, RemoteException
  {

    int exp = librarianClient.getLibrarianList().size();

    librarianClient.addLibrarian(librarian);
    librarianClient.removeLibrarian(librarian.getSsn());
    System.out.println(librarianClient.getLibrarianList().size());
    Assertions.assertEquals(exp,librarianClient.getLibrarianList().size());
  }


  public void clearList() throws SQLException, RemoteException
  {

  for (int i = 1; i<librarianClient.getLibrarianList().size();i++)
  {
    try
    {
      librarianClient.removeLibrarian(librarianClient.getLibrarianList().get(i).getSsn());
      System.out.println(librarianClient.getLibrarianList().get(i).toString());
    }catch (NullPointerException e){

    }
    librarianClient.getLibrarianList().removeIf(Objects::isNull);

    System.out.println(librarianClient.getLibrarianList().size());
  }
  }


  @Test
  public void addMultiple() throws SQLException, RemoteException
  {
    int exp = librarianClient.getLibrarianList().size();
    librarianClient.addLibrarian(librarian);
    exp++;
    librarianClient.addLibrarian(librarian1);
    exp++;
    librarianClient.addLibrarian(librarian2);
    exp++;
    Assertions.assertEquals(exp,librarianClient.getLibrarianList().size());

  }

  @Test
  public void removeMultiple() throws SQLException, RemoteException
  {

    int exp = librarianClient.getLibrarianList().size();

    librarianClient.addLibrarian(librarian);

    librarianClient.addLibrarian(librarian1);

    librarianClient.addLibrarian(librarian2);

    librarianClient.removeLibrarian(librarian.getSsn());
    librarianClient.removeLibrarian(librarian1.getSsn());
    librarianClient.removeLibrarian(librarian2.getSsn());

    Assertions.assertEquals(exp,librarianClient.getLibrarianList().size());
  }
  @Test
  public void checkAttributesOfAddedElements()
      throws SQLException, RemoteException
  {
    String check = librarian.toString();
    librarianClient.addLibrarian(librarian);
    Assertions.assertEquals(librarianClient.getLibrarianList().get(0).toString(),check);
  //  System.out.println(librarianClient.getLibrarianList().get(0).toString());
  }
  @Test
  public void removeNonExistingElement() throws SQLException, RemoteException
  {
    try
    {
      librarianClient.removeLibrarian(12456);
    }catch (NullPointerException e)
    {
       e.fillInStackTrace();
    }

  }


}
