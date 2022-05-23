package client;

import model.LibraryUser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Collections;

public class LibraryClientRmiTest
{
  private static Registry registry;
  private static LibraryUserClient libraryUserClient;
  private static LibraryUser libraryUser;
  private static LibraryUser libraryUser1;
  private static LibraryUser libraryUser2;

  @BeforeAll public static void setUp()
      throws IOException, NotBoundException, AlreadyBoundException
  {
    registry = LocateRegistry.createRegistry(1023);
    libraryUserClient = new LibraryUserClientImplementation("localhost",Registry.REGISTRY_PORT);
    registry.bind("libraryUser", (Remote) libraryUserClient);
  }

  @BeforeAll public static void setupVariables(){
    libraryUser = new LibraryUser("12345","gianni","nanni","noPass");
    libraryUser1 = new LibraryUser("23456","spyro","girevole","salatare");
    libraryUser2 = new LibraryUser("234567","sapino","marco","aerotecnoca");
  }
  @AfterEach
  public void tearDown() throws SQLException, RemoteException
  {


    for (LibraryUser x: libraryUserClient.getLibraryUserList()){

        libraryUserClient.removeLibraryUser(x.getSSN());

    }
    System.out.println(libraryUserClient.getLibraryUserList().size());

  }
  @Test
  public void addNull() throws SQLException, RemoteException
  {
    //should we be able to add null objects ???
    int exp = libraryUserClient.getLibraryUserList().size();
    libraryUserClient.addLibraryUser(null);
    Assertions.assertEquals(exp,libraryUserClient.getLibraryUserList().size());
  }

  @Test
  public void checkEmptyAtCreation() throws SQLException, RemoteException
  {

    Assertions.assertTrue(libraryUserClient.getLibraryUserList().isEmpty());
  }

  @Test
  public void addOne() throws SQLException, RemoteException
  {
    int exp = libraryUserClient.getLibraryUserList().size();
    libraryUserClient.addLibraryUser(libraryUser1);
    exp++;
    Assertions.assertEquals(exp,libraryUserClient.getLibraryUserList().size());
  }
  @Test
  public void addMultiple() throws SQLException, RemoteException
  {
    int exp = libraryUserClient.getLibraryUserList().size();
    libraryUserClient.addLibraryUser(libraryUser1);
    exp++;
    libraryUserClient.addLibraryUser(libraryUser2);
    exp++;
    libraryUserClient.addLibraryUser(libraryUser);
    exp++;
    Assertions.assertEquals(exp,libraryUserClient.getLibraryUserList().size());
  }

  @Test
  public void removeOne() throws SQLException, RemoteException
  {
    int exp=libraryUserClient.getLibraryUserList().size();
    libraryUserClient.addLibraryUser(libraryUser);
    exp++;
    libraryUserClient.addLibraryUser(libraryUser1);
    libraryUserClient.removeLibraryUser(libraryUser1.getSSN());

    Assertions.assertEquals(exp,libraryUserClient.getLibraryUserList().size());
  }
  @Test
  public void removeMultiple() throws SQLException, RemoteException
  {
    int exp=libraryUserClient.getLibraryUserList().size();
    libraryUserClient.addLibraryUser(libraryUser);

    libraryUserClient.addLibraryUser(libraryUser1);

    libraryUserClient.addLibraryUser(libraryUser2);
    exp++;
    libraryUserClient.removeLibraryUser(libraryUser1.getSSN());
    libraryUserClient.removeLibraryUser(libraryUser.getSSN());

    Assertions.assertEquals(exp,libraryUserClient.getLibraryUserList().size());
  }

}
