package client;



import model.LoanMagazine;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;

public class LoanMagazineRmiTest
{
  private static Registry registry;
  private static LoanMagazineClientImplementation loanMagazineClient;


  @BeforeAll
  public static void setUp()
      throws NotBoundException, IOException, AlreadyBoundException
  {
    registry = LocateRegistry.createRegistry(1111);
    loanMagazineClient = new LoanMagazineClientImplementation("localhost",Registry.REGISTRY_PORT);
    registry.bind("loanMagazine",loanMagazineClient);
  }
  @Test
  public void checkGetLibraryUser() throws RemoteException
  {
    String expected = "gianni";
    Assertions.assertEquals(loanMagazineClient.getUser("12456789909").getFirstName(),expected);

  }

  @Test
  public void checkGetLibraryUserWrongSsn() throws RemoteException
  {
    Assertions.assertEquals(null,loanMagazineClient.getUser("barbapap"));
  }

  @Test
  public void checkAvailableBookList() throws SQLException, RemoteException
  {
    Assertions.assertEquals(1,loanMagazineClient.getAvailableMagazineList().size());
  }

  @Test
  public void addLoanBook() throws SQLException, RemoteException
  {
    loanMagazineClient.addMagazineLoan(new LoanMagazine(6,"hjk"));
  }
  @Test
  public void addMultipleLoans () throws SQLException, RemoteException
  {
    loanMagazineClient.addMagazineLoan(new LoanMagazine(3,"adkkjk"));
    loanMagazineClient.addMagazineLoan(new LoanMagazine(6,"hjasdf"));
    loanMagazineClient.addMagazineLoan(new LoanMagazine(61,"hdada"));
  }
   @Test
  public void returnMagazineTest() throws RemoteException
   {
    loanMagazineClient.returnMagazine(4);
  }

  @Test
  public void checkUserLoans() throws RemoteException
  {
    Assertions.assertEquals(1, loanMagazineClient.getUserLoans("").size());
  }
}